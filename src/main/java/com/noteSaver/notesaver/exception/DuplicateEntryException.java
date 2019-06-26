package com.noteSaver.notesaver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s already exist with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
