package com.projectexam.exam.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projectexam.exam.CreateDtos.ConsultationCreateDto;
import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface ConsultationService extends GenericService<ConsultationDto, Long> {

    Page<ConsultationDto> searchConsultByNomPAT(String nomPAT, Pageable pageable);

    Page<ConsultationDto> getConsultationsByPatientNss(Long nss, Pageable pageable);

    ConsultationDto createConsultation(ConsultationCreateDto consultation);

    ConsultationDto addPrescription(Long numero, List<MedicamentDto> medicament);

    ConsultationDto updateConsultation(Long numero, com.projectexam.exam.CreateDtos.ConsultationCreateDto update);

    ConsultationDto attachDocument(Long numero, MultipartFile file);

    Resource getDocument(Long numero);
    
}
