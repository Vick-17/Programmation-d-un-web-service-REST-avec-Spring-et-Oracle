package com.projectexam.exam.Controllers;

/**
 * Contrôleur REST pour Prescription (placeholder).
 * Les opérations principales sont gérées via Consultation.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController extends GenericController<PrescriptionDto, Long, PrescriptionService> {

    public PrescriptionController(PrescriptionService service) {
        super(service);
    }
    
}
