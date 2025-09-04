package com.projectexam.exam.Services;

/**
 * Implémentation du service Médecin.
 * Valide la création (unicité matricule, encodage mot de passe)
 * et expose la recherche paginée par nom/matricule.
 */

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectexam.exam.CreateDtos.MedecinCreateDto;
import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.MedecinMapper;
import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.MedecinRepository;
import com.projectexam.exam.Errors.ConflictException;

@Service
public class MedecinServiceImpl extends GenericServiceImpl<Medecin, MedecinDto, Long, MedecinRepository, MedecinMapper> implements MedecinService {
    
    public MedecinServiceImpl(MedecinRepository repository, MedecinMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public MedecinDto createMedecin(MedecinCreateDto medecin){
        if (medecin == null) {
            throw new IllegalArgumentException("Requête invalide");
        }

        Optional<Medecin> optionalMedecin = repository.findByMatricule(medecin.getMatricule());
        if (optionalMedecin.isPresent()) {
            throw new ConflictException("Le matricule est déjà utilisé");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (medecin.getPassword() == null || medecin .getPassword().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe est requis");
        }
        if (medecin.getNomMED() == null || medecin.getNomMED().isBlank()) {
            throw new IllegalArgumentException("Le nom est requis");
        }

        Medecin entity = new Medecin();
        entity.setMatricule(medecin.getMatricule());
        entity.setNomMED(medecin.getNomMED());
        entity.setPassword(bCryptPasswordEncoder.encode(medecin.getPassword()));
        Medecin saved = repository.saveAndFlush(entity);
        return toDto(saved);
    }

    @Override
    public MedecinDto searchMedByName(String nomMED) {
        Optional<Medecin> optionalMedecin = repository.findByNomMED(nomMED);
        return optionalMedecin.map(mapper::toDto).orElse(null);
    }

    @Override
    public Page<MedecinDto> searchMedecins(String search, Pageable pageable) {
        if (search == null || search.isBlank()) {
            return repository.findAll(pageable).map(mapper::toDto);
        }
        try {
            Long matricule = Long.parseLong(search.trim());
            return repository.findByMatricule(matricule, pageable).map(mapper::toDto);
        } catch (NumberFormatException e) {
            return repository.findByNomMEDContainingIgnoreCase(search.trim(), pageable).map(mapper::toDto);
        }
    }

}
