package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.PrescriptionDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.PrescriptionService;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController extends GenericController<PrescriptionDto, PrescriptionService> {

    public PrescriptionController(PrescriptionService service) {
        super(service);
    }
    
}
