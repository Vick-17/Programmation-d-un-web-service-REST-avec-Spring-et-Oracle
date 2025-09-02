package com.projectexam.exam.Services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.PatientMapper;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Repositories.PatientRepository;

@Service
public class PatientServiceImpl extends GenericServiceImpl<Patient, PatientDto, Long, PatientRepository, PatientMapper> implements PatientService {
    
    public PatientServiceImpl(PatientRepository repository, PatientMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public PatientDto createPatient(PatientCreateDto patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Requête invalide");
        }
        Optional<Patient> optionalPatient = repository.findBynSS(patient.getNSS());
        if (optionalPatient.isPresent()) {
            throw new RuntimeException("Le nSS est déjà utilisée.");
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

    @Override
    public PatientDto searchPatByNomPat(String nomPat) {
        Optional<Patient> optionalPatient = repository.findByNomPAT(nomPat);
        return optionalPatient.map(mapper::toDto).orElse(null);
    }

}
