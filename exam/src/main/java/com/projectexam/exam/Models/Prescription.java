package com.projectexam.exam.Models;

import com.projectexam.exam.Generic.BaseEntity;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription")
public class Prescription extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultation_numero", nullable = false)
    private Consultation consultation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicament_code", nullable = false)
    private Medicament medicament;

    @Column(name = "nb_prises", nullable = false)
    private int nbPrises;

}