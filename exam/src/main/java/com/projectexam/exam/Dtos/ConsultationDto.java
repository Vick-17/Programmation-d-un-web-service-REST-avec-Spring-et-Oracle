package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Models.Prescription;
import lombok.Value;

@Value
public class ConsultationDto implements Serializable {

    int numero;
    LocalDate date;
    Medecin medecin;
    Patient patient;
    Set<Prescription> prescriptions;

}
