package com.trots.oxtest.exception;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Class<?> clazz, Object id) {
        super(String.format("%s not found for id: %s", clazz.getSimpleName(), id));
    }
}
