package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.util.List;
import com.projectexam.exam.Models.Consultation;

import lombok.Value;

@Value
public class MedecinDto implements Serializable {
    
    int matricule;
    String nomMED;
    List<Consultation> consultations;
    
}
