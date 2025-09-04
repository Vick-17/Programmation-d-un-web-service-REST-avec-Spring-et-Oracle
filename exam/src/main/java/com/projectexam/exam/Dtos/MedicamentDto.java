package com.projectexam.exam.Dtos;

/**
 * DTO en lecture pour Médicament.
 */

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class MedicamentDto implements Serializable {
    
    Long code;
    String libelle;
    List<PrescriptionDto> prescriptions;
    
}
