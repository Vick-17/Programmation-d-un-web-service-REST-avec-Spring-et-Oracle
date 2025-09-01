package com.projectexam.exam.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Patient;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findBynSS(Long nSS);
}
