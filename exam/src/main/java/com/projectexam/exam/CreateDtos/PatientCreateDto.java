package com.projectexam.exam.CreateDtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientCreateDto {

    @JsonProperty("nss")
    @JsonAlias({"NSS", "nSS"})
    private Long nSS;

    private String password;

    private String nomPAT;
}

