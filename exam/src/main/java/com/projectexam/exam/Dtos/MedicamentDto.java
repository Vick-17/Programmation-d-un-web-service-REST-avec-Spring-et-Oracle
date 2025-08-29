package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.util.List;
import com.projectexam.exam.Models.Prescription;

import lombok.Value;

@Value
public class MedicamentDto implements Serializable {
    
    int code;
    String libelle;
    List<Prescription> prescriptions;
    
}
