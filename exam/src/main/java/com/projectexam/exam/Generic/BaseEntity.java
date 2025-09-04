package com.projectexam.exam.Generic;

/**
 * BaseEntity utilitaire (héritée par certaines entités comme Prescription)
 * pouvant porter un identifiant technique ou des méta-informations communes.
 */

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@MappedSuperclass
@Getter
@Setter
@Accessors(chain = true)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
