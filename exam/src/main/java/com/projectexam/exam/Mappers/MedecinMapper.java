package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Medecin;

@Mapper
public interface MedecinMapper extends GenericMapper<MedecinDto, Medecin> {
    
}
