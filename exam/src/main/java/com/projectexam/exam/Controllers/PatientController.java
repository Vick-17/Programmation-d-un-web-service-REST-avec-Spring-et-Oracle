package com.projectexam.exam.Controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectexam.exam.CreateDtos.PatientCreateDto;
import com.projectexam.exam.Dtos.PatientDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.PatientService;

/**
 * Endpoints REST pour la ressource Patient.
 * <p>
 * Hérite des opérations CRUD génériques via {@link GenericController} et expose
 * des cas d’usage spécifiques: inscription et recherche paginée.
 */
@RestController
@RequestMapping("/patient")
public class PatientController extends GenericController<PatientDto, Long, PatientService> {
    public PatientController(PatientService service) {
        super(service);
    }

    /**
     * Inscription/Création d’un patient.
     *
     * @param request payload de création (NSS, nom, mot de passe)
     * @return patient créé
     */
    @PostMapping("/register")
    public PatientDto createPatient(@RequestBody PatientCreateDto request){
        return service.createPatient(request);
    }

    /**
     * Recherche d’un patient par nom exact.
     *
     * @param nomPAT nom à chercher
     * @return patient trouvé ou null
     */
    @GetMapping("/search/{nomPAT}")
    public PatientDto searchPatient(@PathVariable String nomPAT) {
        return service.searchPatByNomPat(nomPAT);
    }

    /**
     * Recherche paginée de patients par nom (contains, insensible à la casse)
     * ou par NSS si la valeur est numérique.
     *
     * @param search   terme à chercher (nom ou NSS)
     * @param pageable paramètres de pagination/tri
     * @return page de patients
     */
    @GetMapping(params = "search")
    public ResponseEntity<Page<PatientDto>> searchPatients(@RequestParam String search, Pageable pageable) {
        return ResponseEntity.ok(service.searchPatients(search, pageable));
    }

}
