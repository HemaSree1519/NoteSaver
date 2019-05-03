package com.noteSaver.notesaver.repository;

import com.noteSaver.notesaver.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findByEmail(@Param("email") String email);
}
