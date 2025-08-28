package com.projectexam.exam.Services;

import org.springframework.stereotype.Service;

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.PrescriptionMapper;
import com.projectexam.exam.Models.Prescription;
import com.projectexam.exam.Repositories.PrescriptionRepository;

@Service
public class PrescriptionServiceImpl extends GenericServiceImpl<Prescription, PrescriptionDto, PrescriptionRepository, PrescriptionMapper> implements PrescriptionService {
    
    public PrescriptionServiceImpl(PrescriptionRepository repository, PrescriptionMapper mapper) {
        super(repository, mapper);
    }
}
