package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.ConsultationService;

@RestController
@RequestMapping("/consultation")
public class ConsultationController extends GenericController<ConsultationDto, ConsultationService> {
    
    public ConsultationController(ConsultationService service) {
        super(service);
    }
}
