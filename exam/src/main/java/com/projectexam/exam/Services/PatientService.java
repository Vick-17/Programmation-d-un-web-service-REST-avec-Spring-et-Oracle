package com.projectexam.exam.Services;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericService;

public interface PatientService extends GenericService<PatientDto, Long> {

    PatientDto createPatient(PatientCreateDto request);
    
}
