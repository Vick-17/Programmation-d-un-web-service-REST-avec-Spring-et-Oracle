package com.projectexam.exam.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.CreateDtos.ConsultationCreateDto;
import com.projectexam.exam.Dtos.ConsultationDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.ConsultationService;

@RestController
@RequestMapping("/consultation")
public class ConsultationController extends GenericController<ConsultationDto, Long, ConsultationService> {
    
    public ConsultationController(ConsultationService service) {
        super(service);
    }

    @GetMapping("/patientByName")
    public Page<ConsultationDto> searchConsultByNomPAT(@RequestParam String nomPAT, Pageable pageable) {
        return service.searchConsultByNomPAT(nomPAT, pageable);
    }

    @GetMapping("/patientByNss")
    public Page<ConsultationDto> getConsultationsByPatient(@RequestParam Long nss, Pageable pageable) {
        return service.getConsultationsByPatientNss(nss, pageable);
    }

    @PostMapping("/create")
    public ConsultationDto createConsultation(@RequestBody ConsultationCreateDto consultation) {
        return service.createConsultation(consultation);
    }

    @PutMapping("/{numero}")
    public ConsultationDto updateConsultation(@PathVariable Long numero, @RequestBody ConsultationCreateDto update) {
        return service.updateConsultation(numero, update);
    }
}
