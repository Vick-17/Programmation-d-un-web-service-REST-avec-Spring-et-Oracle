package com.projectexam.exam.Repositories;

/**
 * Repository Spring Data pour Prescription.
 */

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    
}
