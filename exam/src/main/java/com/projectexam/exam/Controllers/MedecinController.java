package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.CreateDtos.MedecinCreateDto;
import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.MedecinService;

@RestController
@RequestMapping("/medecin")
public class MedecinController extends GenericController<MedecinDto, Long, MedecinService> {
    
    public MedecinController(MedecinService service) {
        super(service);
    }

    @PostMapping("/register")
    public MedecinDto createMedecin(@RequestBody MedecinCreateDto medecin) {
        return service.createMedecin(medecin);
    }

    @GetMapping("/search/{nomMed}")
    public MedecinDto searchMedecin(@PathVariable String nomMed) {
        return service.searchMedByName(nomMed);
    }
}
