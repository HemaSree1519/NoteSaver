package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.Note;
import com.noteSaver.notesaver.service.NoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteController noteController;
    @Mock
    private NoteService noteService;
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
        when(noteService.addNote(note)).thenReturn(note);
        Note noteFromService = noteService.addNote(note);
        when(noteController.addNote(Mockito.any(Note.class))).thenReturn(noteFromService);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/notesaver/notes/add")
                .accept(MediaType.APPLICATION_JSON).content(noteJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(noteJson, result.getResponse().getContentAsString());
    }

    @Test
    public void givenValidEmail_whenGetNotes_thenReturnAllNotesOfGivenEmail() throws Exception {
        List<Note> list = Arrays.asList(note);
        when(noteService.getNotes("testMail@gmail.com")).thenReturn(list);
        List<Note> listFromService = noteService.getNotes("testMail@gmail.com");
        when(noteController.getNotes("testMail@gmail.com")).thenReturn(listFromService);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/notes/all/testMail@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String expectedJson = "[" + noteJson + "]";
        assertEquals(expectedJson, mvcResult.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
    }

    @Test
    public void givenInvalidEmail_whenGetNotes_thenReturnEmptyList() throws Exception {
        List<Note> emptyList = Arrays.asList();
        when(noteService.getNotes("invalidMail@gmail.com")).thenReturn(emptyList);
        List<Note> emptyListFromService = noteService.getNotes("invalidMail@gmail.com");
        when(noteController.getNotes("invalidMail@gmail.com")).thenReturn(emptyListFromService);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/notes/all/invalidMail@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(0, mvcResult.getResponse().getContentLength());
    }

    @Test
    public void givenIdAndEditedNote_whenUpdateNote_thenReturnSuccessResponse() throws Exception {
        note.setTitle("Updated title");
        String updatedNoteJson = "{\"id\":1,\"title\":\"TestTitle\",\"content\":\"This is a testing note\"," +
                "\"email\":\"tester@gmail.com\",\"updatedAt\":null,\"createdAt\":null}";
        when(noteService.updateNote((long) 1, note)).thenReturn(note);
        Note updatedNoteFromService = noteService.updateNote((long) 1, note);
        when(noteController.updateNote((long) 1, note)).thenReturn(updatedNoteFromService);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/notesaver/notes/1/update")
                .accept(MediaType.APPLICATION_JSON).content(updatedNoteJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void givenInvalidIdAndEditedNote_whenUpdateNote_thenThrowExceptionOrEmptyNote() throws Exception {
        note.setTitle("Updated title");
        String updatedNoteJson = "{\"id\":1,\"title\":\"TestTitle\",\"content\":\"This is a testing note\"," +
                "\"email\":\"tester@gmail.com\",\"updatedAt\":null,\"createdAt\":null}";
        when(noteController.updateNote((long) 5, note)).thenThrow(new ResourceNotFoundException("Note", "id", "5"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/notesaver/notes/5/update")
                .accept(MediaType.APPLICATION_JSON).content(updatedNoteJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("", result.getResponse().getContentAsString());
    }

    @Test
    public void givenValidIdAndEmail_whenDeleteNote_thenReturnSuccessResponse() throws Exception {
        when(noteService.deleteNote((long) 1, "tester@gmail.com"))
                .thenReturn(ResponseEntity.ok().build());
        ResponseEntity deleteStatus = noteService.deleteNote((long) 1, "tester@gmail.com");
        when(noteController.deleteNote((long) 1, "tester@gmail.com"))
                .thenReturn(deleteStatus);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/notesaver/notes/tester@gmail.com/1/delete");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void givenInvalidId_whenDeleteNote_thenThrowException() throws Exception {
        when(noteController.deleteNote((long) 2, "tester@gmail.com"))
                .thenThrow(new ResourceNotFoundException("Note", "id", "2"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/notesaver/notes/tester@gmail.com/2/delete");
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals("Note not found with id : '2'", Objects.requireNonNull(result.getResolvedException()).getMessage());
    }
}
