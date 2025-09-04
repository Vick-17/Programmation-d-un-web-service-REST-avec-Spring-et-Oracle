package com.projectexam.exam.CreateDtos;

/**
 * DTO d'inscription pour Patient (NSS, nom, mot de passe).
 */

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDto {

    @JsonProperty("nss")
    @JsonAlias({"NSS", "nSS"})
    @NotNull(message = "NSS requis")
    private Long nSS;

    @NotBlank(message = "Mot de passe requis")
    private String password;

    @NotBlank(message = "Nom requis")
    private String nomPAT;
}
