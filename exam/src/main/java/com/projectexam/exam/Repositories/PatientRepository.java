package com.projectexam.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    
}
