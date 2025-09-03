package com.projectexam.exam.CreateDtos;

import java.time.LocalDate;
import java.util.Set;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Dtos.PrescriptionDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationCreateDto {
    
    Long numero;
    LocalDate date;
    MedecinDto medecin;
    PatientDto patient;
    Set<PrescriptionDto> prescriptions;
    
}
