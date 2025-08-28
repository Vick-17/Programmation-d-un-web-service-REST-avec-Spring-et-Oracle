package com.projectexam.exam.Models;

import jakarta.persistence.*;
import java.util.List;

import com.projectexam.exam.Generic.BaseEntity;

@Entity
@Table(name = "medecin")
public class Medecin extends BaseEntity {

    @Column(name = "nom_med", nullable = false)
    private String nomMED;

    @OneToMany(mappedBy = "medecin")
    private List<Consultation> consultations;

}