package com.projectexam.exam.Mappers;

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Consultation;

@Mapper(componentModel = "spring", uses = { PrescriptionMapper.class, MedicamentMapper.class, PatientMapper.class, MedecinMapper.class })
public interface ConsultationMapper extends GenericMapper<ConsultationDto, Consultation> {
}
