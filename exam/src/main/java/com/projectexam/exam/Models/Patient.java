package com.projectexam.exam.Models;

import java.util.List;

import com.projectexam.exam.Generic.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Patient extends BaseEntity {

    @Column(name = "nom_pat", nullable = false)
    private String nomPAT;

    // 1..n consultations auxquelles il assiste
    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;

    // getters/setters
}
