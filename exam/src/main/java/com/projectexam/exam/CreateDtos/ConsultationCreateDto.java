package com.projectexam.exam.CreateDtos;

/**
 * DTO de création/mise à jour pour Consultation.
 * Permet de fournir la date, le patient ciblé (via son NSS)
 * et des prescriptions à créer.
 */

import java.time.LocalDate;
import java.util.Set;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Dtos.PrescriptionDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationCreateDto {
    
    Long numero;
    @NotNull(message = "Date requise")
    LocalDate date;
    MedecinDto medecin;
    @NotNull(message = "Patient requis")
    PatientDto patient;
    @Valid
    Set<PrescriptionDto> prescriptions;
    
}
