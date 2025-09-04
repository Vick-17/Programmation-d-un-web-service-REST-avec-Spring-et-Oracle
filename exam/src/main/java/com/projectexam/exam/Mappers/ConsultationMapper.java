package com.projectexam.exam.Mappers;

/**
 * MapStruct mapper pour Consultation ⇄ ConsultationDto.
 * Utilise d'autres mappers pour éviter les cycles.
 */

import org.mapstruct.Mapper;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericMapper;
import com.projectexam.exam.Models.Consultation;

@Mapper(componentModel = "spring", uses = { PrescriptionMapper.class, MedicamentMapper.class, PatientMapper.class, MedecinMapper.class })
public interface ConsultationMapper extends GenericMapper<ConsultationDto, Consultation> {
}
