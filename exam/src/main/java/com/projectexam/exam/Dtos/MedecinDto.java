package com.projectexam.exam.Dtos;

/**
 * DTO en lecture pour Médecin.
 */

import java.io.Serializable;
import java.util.List;
import com.projectexam.exam.Models.Consultation;

import lombok.Value;

@Value
public class MedecinDto implements Serializable {
    
    Long matricule;
    String nomMED;
    List<ConsultationDto> consultations;
    
}
