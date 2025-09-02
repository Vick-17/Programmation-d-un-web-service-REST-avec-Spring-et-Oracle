package com.projectexam.exam.Repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Consultation;


public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Page<Consultation> findByPatient_NomPATIgnoreCase(String nomPAT, Pageable pageable);
}
