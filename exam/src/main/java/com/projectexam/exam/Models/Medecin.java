package com.projectexam.exam.Models;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "medecin")
public class Medecin {

    @Id
    @Column(name = "matricule")
    private Long matricule;

    @Column(name = "nom_med", nullable = false)
    private String nomMED;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;

}
