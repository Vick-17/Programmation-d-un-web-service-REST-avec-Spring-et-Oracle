package com.projectexam.exam.Generic;

/**
 * Mapper générique pour convertir un DTO en entité JPA et inversement.
 * Implémenté concrètement via MapStruct dans les mappers spécifiques.
 */

public interface GenericMapper<D, E> {

    D toDto(E entity);

    E toEntity(D dto);
}
