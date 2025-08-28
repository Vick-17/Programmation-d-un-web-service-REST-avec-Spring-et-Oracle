package com.projectexam.exam.Dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.projectexam.exam.Generic.BaseDto;
import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Models.Patient;
import com.projectexam.exam.Models.Prescription;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Value
public class ConsultationDto extends BaseDto implements Serializable {

    LocalDate date;
    Medecin medecin;
    Patient patient;
    Set<Prescription> prescriptions;

}
