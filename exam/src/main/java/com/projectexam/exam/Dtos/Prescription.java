package com.projectexam.exam.Dtos;

import java.io.Serializable;

import com.projectexam.exam.Generic.BaseDto;
import com.projectexam.exam.Models.Consultation;
import com.projectexam.exam.Models.Medicament;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class Prescription extends BaseDto implements Serializable {
    
    Consultation consultation;

    Medicament medicament;

    int nbPrises;
}
