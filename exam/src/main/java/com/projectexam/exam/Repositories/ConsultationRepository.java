package com.projectexam.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Consultation;

public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {
    
}
