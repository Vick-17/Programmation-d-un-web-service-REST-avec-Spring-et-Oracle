package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class PatientDto implements Serializable {
    
    int nSS;
    String nomPAT;
    List<ConsultationDto> consultations;

}
