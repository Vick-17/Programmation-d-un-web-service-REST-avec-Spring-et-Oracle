package com.projectexam.exam.Services;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service métier pour la gestion des patients.
 * <p>
 * Contient les cas d’usage spécifiques (création avec règles de validation,
 * recherche paginée par nom ou numéro de sécurité sociale, etc.).
 */
public interface PatientService extends GenericService<PatientDto, Long> {

    /**
     * Crée un patient en appliquant les règles de validation métiers
     * (unicité du NSS, présence du mot de passe et du nom) et encode le mot de passe.
     *
     * @param request données d’entrée pour la création (NSS, nom, mot de passe)
     * @return le patient créé sous forme de DTO
     * @throws IllegalArgumentException si les champs obligatoires sont manquants
     * @throws RuntimeException si le NSS existe déjà
     */
    PatientDto createPatient(PatientCreateDto request);

    /**
     * Recherche un patient via son nom exact (non paginé).
     *
     * @param nomPAT nom du patient
     * @return le patient correspondant ou null
     */
    PatientDto searchPatByNomPat(String nomPAT);

    /**
     * Recherche paginée de patients par nom (contains, insensible à la casse)
     * ou par NSS si la chaîne fournie est un nombre.
     *
     * @param search   terme de recherche (nom ou NSS)
     * @param pageable pagination/tri Spring
     * @return page de {@link PatientDto}
     */
    Page<PatientDto> searchPatients(String search, Pageable pageable);
    
}
