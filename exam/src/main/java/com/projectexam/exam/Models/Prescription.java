package com.projectexam.exam.Models;

/**
 * Entité JPA Prescription.
 * Représente l'association Consultation ↔ Médicament et la quantité (nbPrises).
 */

import com.projectexam.exam.Generic.BaseEntity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
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
