package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import lombok.Value;

@Value
public class ConsultationDto implements Serializable {

    Long numero;
    LocalDate date;
    MedecinDto medecin;
    PatientDto patient;
    Set<PrescriptionDto> prescriptions;

}
