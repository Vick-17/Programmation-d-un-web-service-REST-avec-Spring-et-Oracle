package com.projectexam.exam.Services;

import org.springframework.stereotype.Service;

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

}
