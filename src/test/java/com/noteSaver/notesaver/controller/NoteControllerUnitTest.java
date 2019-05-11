package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteController noteController;
    private String noteJson;
    private Note note = new Note();

    {
        note.setId((long) 1);
        note.setEmail("tester@gmail.com");
        note.setTitle("TestTitle");
        note.setContent("This is a testing note");
        noteJson = "{\"id\":1,\"title\":\"TestTitle\",\"content\":\"This is a testing note\"," +
                "\"email\":\"tester@gmail.com\",\"updatedAt\":null,\"createdAt\":null}";
    }

    @Test
    public void givenNote_whenAddNote_thenReturnSuccessResponse() throws Exception {
        when(noteController.createNote(Mockito.any(Note.class))).thenReturn(note);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/notesaver/notes/add")
                .accept(MediaType.APPLICATION_JSON).content(noteJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String responseResult = response.getContentAsString();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(noteJson, responseResult);
    }

    @Test
    public void givenEmail_whenGetAllNotesOfUser_thenReturnAllNotesOfUserWithGivenEmail() throws Exception {
        List<Note> list = Arrays.asList(note);
        when(noteController.getAllNotesOfUser("testMail@gmail.com")).thenReturn(list);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/notes/all/testMail@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void givenInvalidEmail_whenGetAllNotesOfUser_thenReturnEmptyList() throws Exception {
        List<Note> list = Arrays.asList(note);
        when(noteController.getAllNotesOfUser("tester@gmail.com")).thenReturn(list);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/notes/all/invalid@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(0, mvcResult.getResponse().getContentLength());
    }

    @Test
    public void givenIdAndEditedNote_whenUpdateNote_thenReturnSuccessUpdatedResponse() throws Exception {
        note.setTitle("Updated title");
        String updatedNoteJson = "{\"id\":1,\"title\":\"TestTitle\",\"content\":\"This is a testing note\"," +
                "\"email\":\"tester@gmail.com\",\"updatedAt\":null,\"createdAt\":null}";
        when(noteController.updateNote((long) 1, note)).thenReturn(note);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/notesaver/notes/1/update")
                .accept(MediaType.APPLICATION_JSON).content(updatedNoteJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }
}
