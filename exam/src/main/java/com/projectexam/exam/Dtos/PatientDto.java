package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.util.List;

import com.projectexam.exam.Generic.BaseDto;
import com.projectexam.exam.Models.Consultation;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class PatientDto extends BaseDto implements Serializable {
    
    String nomPat;
    List<Consultation> consultations;

}
