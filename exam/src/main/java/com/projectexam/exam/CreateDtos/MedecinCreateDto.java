package com.projectexam.exam.CreateDtos;

/**
 * DTO d'inscription pour Médecin (matricule, nom, mot de passe).
 */

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class MedecinCreateDto {
    
    @NotNull(message = "Matricule requis")
    private Long matricule;

    @NotBlank(message = "Nom requis")
    private String nomMED;

    @NotBlank(message = "Mot de passe requis")
    private String password;
}
