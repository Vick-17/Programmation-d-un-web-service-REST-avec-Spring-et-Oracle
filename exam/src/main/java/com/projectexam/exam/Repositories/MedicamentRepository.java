package com.projectexam.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    
}
