package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController extends GenericController<PatientDto, Long, PatientService> {
    public PatientController(PatientService service) {
        super(service);
    }

    @PostMapping("/register")
    public PatientDto createPatient(@RequestBody PatientCreateDto request){
        return service.createPatient(request);
    }
}
