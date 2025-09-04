package com.projectexam.exam.Mappers;

/**
 * MapStruct mapper pour Patient ⇄ PatientDto.
 * Ignore la liste des consultations pour éviter les boucles.
 */

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Patient;

@Mapper(componentModel = "spring")
public interface PatientMapper extends GenericMapper<PatientDto, Patient> {

    @Override
    @Mapping(target = "consultations", ignore = true)
    Patient toEntity(PatientDto dto);

    @Override
    @Mapping(target = "consultations", ignore = true)
    PatientDto toDto(Patient entity);
    
}
