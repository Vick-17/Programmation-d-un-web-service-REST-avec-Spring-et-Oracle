package com.projectexam.exam.Dtos;

/**
 * DTO en lecture pour Prescription.
 */

import java.io.Serializable;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PrescriptionDto implements Serializable {
    
    ConsultationDto consultation;

    @NotNull(message = "Médicament requis")
    MedicamentDto medicament;

    @Min(value = 1, message = "nbPrises doit être >= 1")
    int nbPrises;
}
