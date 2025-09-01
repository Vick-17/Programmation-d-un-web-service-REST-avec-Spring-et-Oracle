package com.projectexam.exam.Generic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class GenericServiceImpl<E, D, ID, R extends JpaRepository<E, ID>, M extends GenericMapper<D, E>>
        implements GenericService<D, ID> {

    protected final R repository;
    protected final M mapper;

    @Override
    public Page<D> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(this::toDto);
    }

    @Override
    public D saveOrUpdate(D dto) {
        return toDto(repository.saveAndFlush(toEntity(dto)));
    }

    

    @Override
    public Optional<D> findById(ID id) {
        return repository.findById(id).map(this::toDto);
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    protected D toDto(E entity) {
        return mapper.toDto(entity);
    }

    protected E toEntity(D dto) {
        return mapper.toEntity(dto);
    }

}
