package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Prescription;

@Mapper(componentModel = "spring", uses = { MedicamentMapper.class })
public interface PrescriptionMapper extends GenericMapper<PrescriptionDto, Prescription> {

    @Override
    @Mapping(target = "consultation", ignore = true)
    PrescriptionDto toDto(Prescription entity);

    @Override
    @Mapping(target = "consultation", ignore = true)
    Prescription toEntity(PrescriptionDto dto);
}
