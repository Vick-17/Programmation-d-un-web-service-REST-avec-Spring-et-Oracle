package com.projectexam.exam.Generic;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
public abstract class GenericController<D, ID, S extends GenericService<D, ID>> {
    protected S service;

    @GetMapping
    public ResponseEntity<Page<D>> findAll(Pageable pageable) {
        Page<D> page = service.findAll(pageable);
        return page.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(page);
    }

    @GetMapping("{id}")
    public ResponseEntity<D> getById(@PathVariable ID id) {
        return ResponseEntity.of(service.findById(id));
    }

    @RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
    public ResponseEntity<D> saveOrUpdate(@RequestBody D dto, HttpServletRequest request) {
        boolean isPost = "POST".equalsIgnoreCase(request.getMethod());
        return ResponseEntity
                .status(isPost ? HttpStatus.CREATED : HttpStatus.OK)
                .body(service.saveOrUpdate(dto));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable ID id) {
        service.deleteById(id);
    }
}
