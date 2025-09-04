package com.projectexam.exam.Models;

/**
 * Entité JPA représentant un patient.
 * Clé primaire fonctionnelle: NSS. Un patient peut avoir plusieurs consultations.
 */

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @Column(name = "n_ss", unique = true)
    private Long nSS;

    @Column(name = "nom_pat", nullable = false)
    private String nomPAT;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

}
