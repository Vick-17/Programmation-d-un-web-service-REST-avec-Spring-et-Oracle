package com.projectexam.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Medecin;

public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
    
}
