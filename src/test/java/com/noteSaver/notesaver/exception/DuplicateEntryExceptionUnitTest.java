package com.noteSaver.notesaver.exception;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuplicateEntryExceptionUnitTest {

    @Test
    public void when_given_parameters_it_return_expected_message(){
        assertEquals("User already exist with email : 'duplicate@email.com'",new DuplicateEntryException("User","email","duplicate@email.com").getMessage());
    }
}
