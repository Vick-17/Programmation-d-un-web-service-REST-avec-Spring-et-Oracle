package com.projectexam.exam.Repositories;

/**
 * Repository Spring Data pour Patient.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Patient;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findBynSS(Long nSS);
    
    Optional<Patient> findByNomPAT(String nomPAT);

    Page<Patient> findByNomPATContainingIgnoreCase(String nomPAT, Pageable pageable);

    Page<Patient> findBynSS(Long nSS, Pageable pageable);
}
