package com.projectexam.exam.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "medicament")
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private long code;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "medicament")
    private Set<Prescription> prescriptions;

}
