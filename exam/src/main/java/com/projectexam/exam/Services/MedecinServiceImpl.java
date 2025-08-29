package com.projectexam.exam.Services;

import org.springframework.stereotype.Service;

import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.MedecinMapper;
import com.projectexam.exam.Models.Medecin;
import com.projectexam.exam.Repositories.MedecinRepository;

@Service
public class MedecinServiceImpl extends GenericServiceImpl<Medecin, MedecinDto, Integer, MedecinRepository, MedecinMapper> implements MedecinService {
    
    public MedecinServiceImpl(MedecinRepository repository, MedecinMapper mapper) {
        super(repository, mapper);
    }

}
