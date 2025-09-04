package com.projectexam.exam.Mappers;

/**
 * MapStruct mapper pour Medicament ⇄ MedicamentDto.
 * Ignore la collection de prescriptions pour éviter la récursion.
 */

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Medicament;

@Mapper(componentModel = "spring")
public interface MedicamentMapper extends GenericMapper<MedicamentDto, Medicament> {

    @Override
    @Mapping(target = "prescriptions", ignore = true)
    MedicamentDto toDto(Medicament entity);

    @Override
    @Mapping(target = "prescriptions", ignore = true)
    Medicament toEntity(MedicamentDto dto);
}
