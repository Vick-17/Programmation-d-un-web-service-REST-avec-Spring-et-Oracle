package com.projectexam.exam.Services;

import com.projectexam.exam.CreateDtos.MedecinCreateDto;
import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericService;

public interface MedecinService extends GenericService<MedecinDto, Long> {

    MedecinDto createMedecin(MedecinCreateDto medecin);

    MedecinDto searchMedByName(String nomMED);
    
}
