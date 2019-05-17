package com.noteSaver.notesaver.controller;


import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.repository.NoteRepository;
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

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/notesaver")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @RequestMapping("/notes/all/{email}")
    public List<Note> getAllNotesOfUser(@PathVariable(value = "email") String email) {
        return noteRepository.findByEmail(email);

    }

    // Create a new Note
    @PostMapping("/notes/add")
    public Note createNote(@Valid @RequestBody Note note) {
        return noteRepository.save(note);
    }

    // Update a Note
    @PutMapping("/notes/{id}/update")
    public Note updateNote(@PathVariable(value = "id") Long noteId, @Valid @RequestBody Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setUpdatedAt(noteDetails.getUpdatedAt());
        return noteRepository.save(note);
    }

    // Delete a Note
    @DeleteMapping("/notes/{email}/{id}/delete")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId, @PathVariable(value = "email") String email) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        if (note.getEmail().equals(email)) {
            noteRepository.delete(note);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("Note", "id", noteId);

    }
}