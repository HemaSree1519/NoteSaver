package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.NoteSaverApplication;
import com.noteSaver.notesaver.model.User;
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

import java.util.Objects;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NoteSaverApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerITTest {
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void addUserTest() {
        User user = new User();
        user.setEmail("tester@gmail.com");
        user.setUserName("tester");
        user.setPassword("password");
        user.setRole("admin");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/add",
                HttpMethod.POST, entity, String.class);
        ResponseEntity<String> response2 = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/add",
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        assertTrue(Objects.requireNonNull(response2.getBody()).contains("User already exist with email : 'tester@gmail.com'"));
    }

    @Test
    public void getUserTest() {
        User user = new User();
        user.setEmail("tester2@gmail.com");
        user.setUserName("tester");
        user.setPassword("password");
        user.setRole("admin");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> postApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/add",
                HttpMethod.POST, entity, String.class);
        HttpEntity<User> entityForGetApi = new HttpEntity<>(null, headers);
        ResponseEntity<String> getApiResponseOfSuccessCase = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/tester2@gmail.com",
                HttpMethod.GET, entityForGetApi, String.class);
        ResponseEntity<String> getApiResponseOfFailCase = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/testerDummy@gmail.com",
                HttpMethod.GET, entityForGetApi, String.class);
        assertTrue(Objects.requireNonNull(getApiResponseOfFailCase.getBody()).contains("user not found with id : 'testerDummy@gmail.com'"));
        assertEquals(postApiResponse.getBody(), getApiResponseOfSuccessCase.getBody());
    }

    @Test
    public void getUsersTest() {
        User user = new User();
        user.setEmail("tester3@gmail.com");
        user.setUserName("tester3");
        user.setPassword("password");
        user.setRole("admin");
        HttpEntity<User> entityForGetApi = new HttpEntity<>(null, headers);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> postApiResponse = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/add",
                HttpMethod.POST, entity, String.class);
        ResponseEntity<String> getApiResponseOfSuccessCase = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users",
                HttpMethod.GET, entityForGetApi, String.class);
        assertNotNull(getApiResponseOfSuccessCase.getBody());
    }
}

