package com.projectexam.exam.Dtos;

import java.io.Serializable;

import lombok.Value;

@Value
public class PrescriptionDto implements Serializable {
    
    ConsultationDto consultation;

    MedicamentDto medicament;

    int nbPrises;
}
