package com.projectexam.exam.Errors;

/**
 * Exception métier pour signaler un conflit applicatif (HTTP 409),
 * par exemple une ressource déjà existante.
 */
public class ConflictException extends RuntimeException {
    public ConflictException(String message) { super(message); }
}

