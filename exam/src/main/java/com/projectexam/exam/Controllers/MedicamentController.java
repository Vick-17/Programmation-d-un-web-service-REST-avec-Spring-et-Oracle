package com.projectexam.exam.Controllers;

/**
 * Contrôleur REST minimal pour la ressource Médicament.
 * Hérite des opérations génériques via GenericController.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.MedicamentService;

@RestController
@RequestMapping("/medicament")
public class MedicamentController extends GenericController<MedicamentDto, Long, MedicamentService> {

    public MedicamentController(MedicamentService service){
        super(service);
    }
    
}
