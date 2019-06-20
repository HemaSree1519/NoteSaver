package com.noteSaver.notesaver.controller;


import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.repository.NoteRepository;
import com.noteSaver.notesaver.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/notesaver")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    NoteService noteService;

    // Get all notes
    @RequestMapping("/notes/all/{email}")
    public List<Note> getNotes(@PathVariable(value = "email") String email) {
        return noteService.getNotes(email);
    }

    // Add a new note
    @PostMapping("/notes/add")
    public Note addNote(@Valid @RequestBody Note note) {
        return noteService.addNote(note);
    }

    // Update a note
    @PutMapping("/notes/{id}/update")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {
        return noteService.updateNote(noteId, noteDetails);
    }

    // Delete a note
    @DeleteMapping("/notes/{email}/{id}/delete")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId, @PathVariable(value = "email") String email) {
        return noteService.deleteNote(noteId, email);
    }
}