package com.projectexam.exam.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import com.projectexam.exam.Generic.BaseEntity;

@Entity
@Table(name = "consultation")
public class Consultation extends BaseEntity {

    @Column(name = "date_consult", nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medecin_matricule", nullable = false)
    private Medecin medecin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_nss", nullable = false)
    private Patient patient;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prescription> prescriptions;

}
