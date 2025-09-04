package com.projectexam.exam.Repositories;

/**
 * Repository Spring Data pour Medecin.
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.projectexam.exam.Models.Medecin;
import java.util.Optional;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    Optional<Medecin> findByMatricule(Long matricule);

    Optional<Medecin> findByNomMED(String nomMED);

    Page<Medecin> findByNomMEDContainingIgnoreCase(String nomMED, Pageable pageable);

    Page<Medecin> findByMatricule(Long matricule, Pageable pageable);
}
