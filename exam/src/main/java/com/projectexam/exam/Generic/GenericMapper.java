package com.projectexam.exam.Generic;

public interface GenericMapper<D, E> {

    D toDto(E entity);

    E toEntity(D dto);
}