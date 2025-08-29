package com.projectexam.exam.Generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GenericService<D, ID> {
    Page<D> findAll(Pageable pageable);

    D saveOrUpdate(D user);

    Optional<D> findById(ID id);

    void deleteById(ID id);
}
