package com.projectexam.exam.Dtos;

import java.io.Serializable;

import com.projectexam.exam.Models.Consultation;
import com.projectexam.exam.Models.Medicament;

import lombok.Value;

@Value
public class PrescriptionDto implements Serializable {
    
    ConsultationDto consultation;

    MedicamentDto medicament;

    int nbPrises;
}
