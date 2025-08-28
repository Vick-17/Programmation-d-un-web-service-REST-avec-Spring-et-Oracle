package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Medicament;

@Mapper
public interface MedicamentMapper extends GenericMapper<MedicamentDto, Medicament> {
    
}
