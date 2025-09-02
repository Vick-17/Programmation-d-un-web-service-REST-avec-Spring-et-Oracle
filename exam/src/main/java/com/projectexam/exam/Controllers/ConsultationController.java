package com.projectexam.exam.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.ConsultationService;

@RestController
@RequestMapping("/consultation")
public class ConsultationController extends GenericController<ConsultationDto, Long, ConsultationService> {
    
    public ConsultationController(ConsultationService service) {
        super(service);
    }

    @GetMapping("/patient")
    public Page<ConsultationDto> searchConsultByNomPAT(@RequestParam String nomPAT, Pageable pageable) {
        return service.searchConsultByNomPAT(nomPAT, pageable);
    }
}
