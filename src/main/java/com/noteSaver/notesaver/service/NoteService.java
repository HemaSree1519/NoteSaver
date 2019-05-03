package com.noteSaver.notesaver.service;

import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService implements INoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Override
    public List<Note> findByEmail(String email) {
        List<Note> notes;
        notes = noteRepository.findByEmail(email);
        return notes;
    }
}
