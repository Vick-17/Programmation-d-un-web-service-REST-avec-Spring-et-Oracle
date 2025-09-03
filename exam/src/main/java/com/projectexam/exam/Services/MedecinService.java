package com.projectexam.exam.Services;

import com.projectexam.exam.CreateDtos.MedecinCreateDto;
import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedecinService extends GenericService<MedecinDto, Long> {

    MedecinDto createMedecin(MedecinCreateDto medecin);

    MedecinDto searchMedByName(String nomMED);

    Page<MedecinDto> searchMedecins(String search, Pageable pageable);
    
}
