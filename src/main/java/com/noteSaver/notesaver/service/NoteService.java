package com.noteSaver.notesaver.service;

import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    @Autowired
    NoteRepository noteRepository;

    public List<Note> getNotes(String email) {
        return noteRepository.findByEmail(email);

    }
    public Note addNote(Note note) {
        return noteRepository.save(note);
    }
    public Note updateNote(Long noteId,Note noteDetails) {

        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setUpdatedAt(noteDetails.getUpdatedAt());
        return noteRepository.save(note);
    }
    public ResponseEntity<?> deleteNote(Long noteId,String email) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
        if (note.getEmail().equals(email)) {
            noteRepository.delete(note);
            return ResponseEntity.ok().build();
        } else throw new ResourceNotFoundException("Note", "id", noteId);

    }
}
