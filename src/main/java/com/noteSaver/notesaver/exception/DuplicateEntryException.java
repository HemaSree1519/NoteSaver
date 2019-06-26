package com.noteSaver.notesaver.exception;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}
