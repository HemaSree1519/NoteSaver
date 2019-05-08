package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.NoteSaverApplication;
import com.noteSaver.notesaver.model.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoteSaverApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class NoteControllerITTest {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();
    private Date date = new Date();
    private Note note = new Note();

    {
        note.setId((long) 1);
        note.setEmail("tester@gmail.com");
        note.setTitle("TestTitle");
        note.setContent("This is a testing note");
        note.setCreatedAt(date);
        note.setUpdatedAt(date);
        String noteJson = "{\"id\":1,\"title\":\"TestTitle\",\"content\":\"This is a testing note\"," +
                "\"email\":\"tester@gmail.com\",\"updatedAt\":" + date + ",\"createdAt\":" + date + "}";
    }

    @Test
    public void createNoteTest() {
        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/add",
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    @Test
    public void getAllNotesTest() {
        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<String> postApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/add",
                HttpMethod.POST, entity, String.class);

        HttpEntity<Note> entityForGetApi = new HttpEntity<>(null, headers);
        ResponseEntity<String> getApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes",
                HttpMethod.GET, entityForGetApi, String.class);
        assertEquals(HttpStatus.OK.value(), getApiResponse.getStatusCodeValue());
    }

    @Test
    public void getAllNotesOfUserTest() {
        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<String> postApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/add",
                HttpMethod.POST, entity, String.class);

        HttpEntity<Note> entityForGetApi = new HttpEntity<>(null, headers);
        ResponseEntity<String> getApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/all/tester@gmail.com",
                HttpMethod.GET, entityForGetApi, String.class);
        assertEquals(HttpStatus.OK.value(), getApiResponse.getStatusCodeValue());
    }

    @Test
    public void getNoteById() {
        HttpEntity<Note> entity = new HttpEntity<>(note, headers);
        ResponseEntity<String> postApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/add",
                HttpMethod.POST, entity, String.class);

        HttpEntity<Note> entityForGetApi = new HttpEntity<>(null, headers);
        ResponseEntity<String> getApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/notes/1",
                HttpMethod.GET, entityForGetApi, String.class);
    }
}
