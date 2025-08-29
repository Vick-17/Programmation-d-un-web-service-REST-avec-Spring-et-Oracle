package com.projectexam.exam.Services;

import org.springframework.stereotype.Service;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.ConsultationMapper;
import com.projectexam.exam.Models.Consultation;
import com.projectexam.exam.Repositories.ConsultationRepository;

@Service
public class ConsultationServiceImpl extends GenericServiceImpl<Consultation, ConsultationDto, Long, ConsultationRepository, ConsultationMapper> implements ConsultationService {

    public ConsultationServiceImpl(ConsultationRepository repository, ConsultationMapper mapper) {
        super(repository, mapper);
    }
    
}
