package com.projectexam.exam.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "medecin")
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "matricule")
    private long matricule;

    @Column(name = "nom_med", nullable = false)
    private String nomMED;

    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;

}
