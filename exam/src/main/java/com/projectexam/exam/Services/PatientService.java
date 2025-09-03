package com.projectexam.exam.Services;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService extends GenericService<PatientDto, Long> {

    PatientDto createPatient(PatientCreateDto request);

    PatientDto searchPatByNomPat(String nomPAT);

    Page<PatientDto> searchPatients(String search, Pageable pageable);
    
}
