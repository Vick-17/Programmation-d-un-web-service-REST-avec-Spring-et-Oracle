package com.projectexam.exam.Controllers;

/**
 * Contrôleur REST pour la ressource Médecin.
 * Fournit l'inscription, la recherche exacte et la recherche paginée.
 */

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

import com.projectexam.exam.CreateDtos.MedecinCreateDto;
import com.projectexam.exam.Dtos.MedecinDto;
import com.projectexam.exam.Generic.GenericController;
import com.projectexam.exam.Services.MedecinService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medecin")
public class MedecinController extends GenericController<MedecinDto, Long, MedecinService> {
    
    public MedecinController(MedecinService service) {
        super(service);
    }

    @PostMapping("/register")
    public MedecinDto createMedecin(@Valid @RequestBody MedecinCreateDto medecin) {
        return service.createMedecin(medecin);
    }

    @GetMapping("/search/{nomMed}")
    public MedecinDto searchMedecin(@PathVariable String nomMed) {
        return service.searchMedByName(nomMed);
    }

    @GetMapping(params = "search")
    public ResponseEntity<Page<MedecinDto>> searchMedecins(@RequestParam String search, Pageable pageable) {
        return ResponseEntity.ok(service.searchMedecins(search, pageable));
    }
}
