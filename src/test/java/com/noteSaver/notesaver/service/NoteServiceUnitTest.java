package com.noteSaver.notesaver.service;

import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NoteServiceUnitTest {
    private Note note = new Note();
    @InjectMocks
    private NoteService noteService;
    @Mock
    private NoteRepository noteRepository;


    {
        note.setId((long) 1);
        note.setEmail("UnitTester@gmail.com");
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
    }

    @Test
    public void when_addNote_it_should_return_saved_note() {
        when(noteRepository.save(note)).thenReturn(note);
        Note created = noteService.addNote(note);
        assertThat(created.getEmail()).isSameAs(note.getEmail());
    }

    @Test
    public void when_getNotes_it_should_return_list_of_notes() {
        Note note1 = new Note();
        note1.setEmail("UnitTester@gmail.com");
        note1.setCreatedAt(new Date());
        note1.setUpdatedAt(new Date());
        List<Note> list = Arrays.asList(note, note1);
        when(noteRepository.findByEmail(any(String.class))).thenReturn(list);
        assertEquals(list, noteService.getNotes("UnitTester@gmail.com"));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void when_updateNote_with_non_existing_note_it_should_throw_exception() {
        when(noteRepository.findById((long) 1)).thenThrow(new ResourceNotFoundException("Note", "id", 1));
        noteService.updateNote(note.getId(), note);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void when_deleteNote_with_non_existing_note_it_should_throw_exception() {
        when(noteRepository.findById((long) 1)).thenThrow(new ResourceNotFoundException("Note", "id", 1));
        noteService.deleteNote(note.getId(), note.getEmail());
    }
}
