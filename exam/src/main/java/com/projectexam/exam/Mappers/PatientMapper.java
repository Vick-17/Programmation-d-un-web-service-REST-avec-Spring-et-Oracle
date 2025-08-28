package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Patient;

@Mapper
public interface PatientMapper extends GenericMapper<PatientDto, Patient> {
    
}
