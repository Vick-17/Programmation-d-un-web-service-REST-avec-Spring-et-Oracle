package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Mappers.MedecinMapper;
import com.projectexam.exam.Services.MedecinService;

@RestController
@RequestMapping("/medecin")
public class MedecinController extends GenericController<MedecinDto, MedecinService> {
    
    public MedecinController(MedecinService service) {
        super(service);
    }
}
