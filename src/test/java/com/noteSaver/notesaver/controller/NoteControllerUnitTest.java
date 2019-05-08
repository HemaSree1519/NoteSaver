package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private  NoteController noteController;

    @Test
    public void getAllNotesTest() throws Exception {
        Note note = new Note();
        note.setId((long) 1);
        note.setEmail("tester@gmail.com");
        note.setTitle("TestTitle");
        note.setContent("This is a testing note");
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        List<Note> list = Arrays.asList(note);
        when(noteController.getAllNotes()).thenReturn(list);
        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.get("/notesaver/notes")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }
}
