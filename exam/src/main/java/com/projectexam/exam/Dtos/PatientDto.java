package com.projectexam.exam.Dtos;

/**
 * DTO en lecture pour Patient (NSS, nom, et consultations associées).
 */

import java.io.Serializable;
import java.util.List;

import lombok.Value;

@Value
public class PatientDto implements Serializable {
    
    Long nSS;
    String nomPAT;
    List<ConsultationDto> consultations;

}
