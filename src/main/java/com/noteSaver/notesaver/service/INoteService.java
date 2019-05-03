package com.noteSaver.notesaver.service;

import com.noteSaver.notesaver.model.Note;

import java.util.List;

public interface INoteService {
    public List<Note> findByEmail(String email);
}
