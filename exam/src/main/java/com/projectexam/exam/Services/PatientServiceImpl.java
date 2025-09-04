package com.projectexam.exam.Services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.PatientMapper;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.PatientRepository;
import com.projectexam.exam.Errors.ConflictException;

/**
 * Implémentation du service de gestion des patients.
 * <p>
 * Responsabilités principales:
 * - Encodage du mot de passe lors de la création
 * - Validation des données d’entrée
 * - Vérification d’unicité du NSS
 * - Recherche paginée par nom ou NSS
 */
@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient, PatientDto, Long, PatientRepository, PatientMapper> implements PatientService {
    
    public PatientServiceImpl(PatientRepository repository, PatientMapper mapper) {
        super(repository, mapper);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PatientDto createPatient(PatientCreateDto patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Requête invalide");
        }
        Optional<Patient> optionalPatient = repository.findBynSS(patient.getNSS());
        if (optionalPatient.isPresent()) {
            throw new ConflictException("Le NSS est déjà utilisé");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (patient.getPassword() == null || patient.getPassword().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe est requis.");
        }
        if (patient.getNomPAT() == null || patient.getNomPAT().isBlank()) {
            throw new IllegalArgumentException("Le nom est requis.");
        }

        Patient entity = new Patient();
        entity.setNSS(patient.getNSS());
        entity.setNomPAT(patient.getNomPAT());
        entity.setPassword(bCryptPasswordEncoder.encode(patient.getPassword()));
        Patient saved = repository.saveAndFlush(entity);
        return mapper.toDto(saved);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PatientDto searchPatByNomPat(String nomPat) {
        Optional<Patient> optionalPatient = repository.findByNomPAT(nomPat);
        return optionalPatient.map(mapper::toDto).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<PatientDto> searchPatients(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return repository.findAll(pageable).map(mapper::toDto);
        }
        // Déterminer si la recherche correspond à un NSS numérique
        try {
            Long nss = Long.parseLong(search.trim());
            return repository.findBynSS(nss, pageable).map(mapper::toDto);
        } catch (NumberFormatException e) {
            return repository.findByNomPATContainingIgnoreCase(search.trim(), pageable).map(mapper::toDto);
        }
    }

}
