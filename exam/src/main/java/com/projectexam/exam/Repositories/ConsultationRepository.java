package com.projectexam.exam.Repositories;

/**
 * Repository Spring Data pour Consultation.
 * Expose des recherches par nom patient (insensible à la casse) et par NSS.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectexam.exam.Models.Consultation;


public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    Page<Consultation> findByPatient_NomPATIgnoreCase(String nomPAT, Pageable pageable);

    @Query("select c from Consultation c where c.patient.nSS = :nss")
    Page<Consultation> findByPatientNss(@Param("nss") Long nss, Pageable pageable);
}
