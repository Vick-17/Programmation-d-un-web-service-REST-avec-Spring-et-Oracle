package com.projectexam.exam.Generic;

/**
 * Contrat générique des services d'accès/gestion de données (DTO).
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<D, ID> {
    Page<D> findAll(Pageable pageable);

    D saveOrUpdate(D user);

    Optional<D> findById(ID id);

    void deleteById(ID id);
}
