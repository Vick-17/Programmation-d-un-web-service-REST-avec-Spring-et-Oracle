package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.util.List;

import com.projectexam.exam.Generic.BaseDto;
import com.projectexam.exam.Models.Consultation;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class MedecinDto extends BaseDto implements Serializable {
    
    String nomMED;
    List<Consultation> consultations;
    
}
