package com.projectexam.exam.Services;

/**
 * Service métier pour la gestion des prescriptions (associations Consultation ↔ Médicament).
 */

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericService;

public interface PrescriptionService extends GenericService<PrescriptionDto, Long> {
    
}
