package com.projectexam.exam.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Override
    public Page<ConsultationDto> searchConsultByNomPAT(String nomPAT, Pageable pageable) {
        return repository.findByPatient_NomPATIgnoreCase(nomPAT, pageable).map(mapper::toDto);
    }
}
