package com.projectexam.exam.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.MedicamentService;

@RestController
@RequestMapping("/medicament")
public class MedicamentController extends GenericController<MedicamentDto, Integer, MedicamentService> {

    public MedicamentController(MedicamentService service){
        super(service);
    }
    
}
