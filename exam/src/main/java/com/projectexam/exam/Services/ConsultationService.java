package com.projectexam.exam.Services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericService;

public interface ConsultationService extends GenericService<ConsultationDto, Long> {

    Page<ConsultationDto> searchConsultByNomPAT(String nomPAT, Pageable pageable);
    
}
