package com.projectexam.exam.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.projectexam.exam.CreateDtos.ConsultationCreateDto;
import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.ConsultationMapper;
import com.projectexam.exam.Models.Consultation;
import com.projectexam.exam.Models.Prescription;
import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.ConsultationRepository;
import com.projectexam.exam.Repositories.MedicamentRepository;
import com.projectexam.exam.Repositories.MedecinRepository;
import com.projectexam.exam.Repositories.PatientRepository;
import com.projectexam.exam.Services.Filestorage.FileStorageService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ConsultationServiceImpl extends GenericServiceImpl<Consultation, ConsultationDto, Long, ConsultationRepository, ConsultationMapper> implements ConsultationService {

    private final MedecinRepository medecinRepository;
    private final PatientRepository patientRepository;
    private final MedicamentRepository medicamentRepository;
    private final FileStorageService fileStorageService;

    public ConsultationServiceImpl(ConsultationRepository repository, ConsultationMapper mapper,
                                   MedecinRepository medecinRepository, PatientRepository patientRepository,
                                   MedicamentRepository medicamentRepository, FileStorageService fileStorageService) {
        super(repository, mapper);
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.medicamentRepository = medicamentRepository;
        this.fileStorageService = fileStorageService;
    }
    
    @Override
    public Page<ConsultationDto> searchConsultByNomPAT(String nomPAT, Pageable pageable) {
        return repository.findByPatient_NomPATIgnoreCase(nomPAT, pageable).map(mapper::toDto);
    }

    @Override
    public Page<ConsultationDto> getConsultationsByPatientNss(Long nss, Pageable pageable) {
        if (nss == null) {
            throw new IllegalArgumentException("NSS requis");
        }
        return repository.findByPatientNss(nss, pageable).map(mapper::toDto);
    }

    @Override
    public ConsultationDto createConsultation(ConsultationCreateDto consultation) {
        if (consultation == null) {
            throw new IllegalArgumentException("Requête invalide");
        }

        // Récupérer le médecin connecté
        Medecin medecin = getAuthenticatedMedecin();

        // Récupérer le patient par NSS depuis le DTO fourni
        PatientDto patientDto = consultation.getPatient();
        if (patientDto == null || patientDto.getNSS() == null) {
            throw new IllegalArgumentException("Patient (NSS) requis");
        }
        Patient patient = patientRepository.findBynSS(patientDto.getNSS())
                .orElseThrow(() -> new IllegalArgumentException("Patient introuvable"));

        Consultation entity = new Consultation();
        entity.setDate(consultation.getDate());
        entity.setMedecin(medecin);
        entity.setPatient(patient);

        // Construire les prescriptions si fournies
        if (consultation.getPrescriptions() != null && !consultation.getPrescriptions().isEmpty()) {
            java.util.Set<Prescription> pres = new java.util.HashSet<>();
            consultation.getPrescriptions().forEach(pDto -> {
                if (pDto.getMedicament() == null || pDto.getMedicament().getCode() == null) {
                    throw new IllegalArgumentException("Code médicament requis pour chaque prescription");
                }
                var medicament = medicamentRepository.findById(pDto.getMedicament().getCode())
                        .orElseThrow(() -> new IllegalArgumentException("Médicament introuvable: " + pDto.getMedicament().getCode()));

                Prescription p = new Prescription();
                p.setConsultation(entity);
                p.setMedicament(medicament);
                p.setNbPrises(pDto.getNbPrises());
                pres.add(p);
            });
            entity.setPrescriptions(pres);
        }

        Consultation saved = repository.saveAndFlush(entity);
        return toDto(saved);
    }

    /**
     * Récupère le médecin authentifié depuis le contexte de sécurité.
     */
    private Medecin getAuthenticatedMedecin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new SecurityException("Aucun utilisateur authentifié");
        }
        String principal = authentication.getName();
        try {
            Long matricule = Long.valueOf(principal);
            return medecinRepository.findById(matricule)
                    .orElseThrow(() -> new IllegalStateException("Médecin introuvable"));
        } catch (NumberFormatException e) {
            throw new SecurityException("Identifiant utilisateur invalide");
        }
    }

    @Override
    public ConsultationDto addPrescription(Long numero, List<MedicamentDto> medicaments) {
        var consultation = repository.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable"));

        java.time.LocalDate today = java.time.LocalDate.now();
        if (consultation.getDate() != null && consultation.getDate().isBefore(today)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Consultation passée: modification interdite");
        }

        if (medicaments == null || medicaments.isEmpty()) {
            return toDto(consultation);
        }

        java.util.Set<com.projectexam.exam.Models.Prescription> target = consultation.getPrescriptions();
        if (target == null) target = new java.util.HashSet<>();

        for (MedicamentDto mDto : medicaments) {
            if (mDto == null || mDto.getCode() == null) {
                throw new IllegalArgumentException("Code médicament manquant");
            }
            var medicament = medicamentRepository.findById(mDto.getCode())
                    .orElseThrow(() -> new IllegalArgumentException("Médicament introuvable: " + mDto.getCode()));
            var p = new com.projectexam.exam.Models.Prescription();
            p.setConsultation(consultation);
            p.setMedicament(medicament);
            p.setNbPrises(1); // valeur par défaut
            target.add(p);
        }

        consultation.setPrescriptions(target);
        var saved = repository.saveAndFlush(consultation);
        return toDto(saved);
    }

    @Override
    public ConsultationDto updateConsultation(Long numero, com.projectexam.exam.CreateDtos.ConsultationCreateDto update) {
        var consultation = repository.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable"));

        LocalDate today = java.time.LocalDate.now();
        if (consultation.getDate() != null && consultation.getDate().isBefore(today)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Consultation passée: modification interdite");
        }

        if (update != null) {
            if (update.getDate() != null) {
                consultation.setDate(update.getDate());
            }

            if (update.getPrescriptions() != null && !update.getPrescriptions().isEmpty()) {
                Set<Prescription> target = consultation.getPrescriptions();
                if (target == null) target = new java.util.HashSet<>();

                for (var pDto : update.getPrescriptions()) {
                    if (pDto.getMedicament() == null || pDto.getMedicament().getCode() == null) {
                        throw new IllegalArgumentException("Code médicament requis pour chaque prescription");
                    }
                    var medicament = medicamentRepository.findById(pDto.getMedicament().getCode())
                            .orElseThrow(() -> new IllegalArgumentException("Médicament introuvable: " + pDto.getMedicament().getCode()));
                    var p = new com.projectexam.exam.Models.Prescription();
                    p.setConsultation(consultation);
                    p.setMedicament(medicament);
                    p.setNbPrises(pDto.getNbPrises());
                    target.add(p);
                }
                consultation.setPrescriptions(target);
            }
        }

        var saved = repository.saveAndFlush(consultation);
        return toDto(saved);
    }

    @Override
    public ConsultationDto attachDocument(Long numero, MultipartFile file) {
        var consultation = repository.findById(numero)
                .orElseThrow(() -> new IllegalArgumentException("Consultation introuvable"));

        java.time.LocalDate today = java.time.LocalDate.now();
        if (consultation.getDate() != null && consultation.getDate().isBefore(today)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Consultation passée: modification interdite");
        }

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Fichier requis");
        }

        // Construire un nom de fichier stable: hash + extension
        String filename;
        var hashOpt = fileStorageService.getStorageHash(file);
        String ext = fileStorageService.mimeTypeToExtension(file.getContentType());
        if (hashOpt.isPresent()) {
            filename = hashOpt.get() + ext;
        } else {
            // fallback: original filename sécurisé
            String original = file.getOriginalFilename();
            if (original == null || original.isBlank()) original = "document" + ext;
            filename = original;
        }

        String stored = fileStorageService.store(file, filename);
        consultation.setDocument(stored);
        var saved = repository.saveAndFlush(consultation);
        return toDto(saved);
    }
}
