package com.projectexam.exam.Services;

import org.springframework.stereotype.Service;

import com.projectexam.exam.Dtos.MedicamentDto;
import com.projectexam.exam.Generic.GenericServiceImpl;
import com.projectexam.exam.Mappers.MedicamentMapper;
import com.projectexam.exam.Models.Medicament;
import com.projectexam.exam.Repositories.MedicamentRepository;

@Service
public class MedicamentServiceImpl extends GenericServiceImpl<Medicament, MedicamentDto, Integer, MedicamentRepository, MedicamentMapper> implements MedicamentService {
    
    public MedicamentServiceImpl(MedicamentRepository repository, MedicamentMapper mapper) {
        super(repository, mapper);
    }
    
}
