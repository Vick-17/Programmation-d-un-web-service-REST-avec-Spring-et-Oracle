package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Prescription;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper extends GenericMapper<PrescriptionDto, Prescription> {
    
}
