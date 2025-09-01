package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Medecin;

@Mapper(componentModel = "spring")
public interface MedecinMapper extends GenericMapper<MedecinDto, Medecin> {

    @Override
    @Mapping(target = "consultations", ignore = true)
    Medecin toEntity(MedecinDto dto);

    @Override
    @Mapping(target = "consultations", ignore = true)
    MedecinDto toDto(Medecin entity);
}
