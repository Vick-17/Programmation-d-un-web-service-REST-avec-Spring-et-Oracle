package com.projectexam.exam.Errors;

/**
 * Exception métier pour signaler qu'une ressource est introuvable (HTTP 404).
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) { super(message); }
}

