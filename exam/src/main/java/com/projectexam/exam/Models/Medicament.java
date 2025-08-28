package com.projectexam.exam.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;

import com.projectexam.exam.Generic.BaseEntity;

@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "medicament")
public class Medicament extends BaseEntity {

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "medicament")
    private Set<Prescription> prescriptions;

}