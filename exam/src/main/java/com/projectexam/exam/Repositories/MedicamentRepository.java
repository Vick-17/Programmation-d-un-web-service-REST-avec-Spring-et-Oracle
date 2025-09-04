package com.projectexam.exam.Repositories;

/**
 * Repository Spring Data pour Medicament.
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Medicament;

public interface MedicamentRepository extends JpaRepository<Medicament, Long> {
    
}
