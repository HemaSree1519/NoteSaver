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

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void createUserTest() {
        User user = new User();
        user.setEmail("tester3@gmail.com");
        user.setUserName("tester");
        user.setPassword("password");
        user.setRole("admin");
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/notesaver/users/add",
                HttpMethod.POST, entity, String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }
}

