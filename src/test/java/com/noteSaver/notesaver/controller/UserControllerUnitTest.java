package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.exception.DuplicateEntryException;
import com.noteSaver.notesaver.exception.ResourceNotFoundException;
import com.noteSaver.notesaver.model.User;
import com.noteSaver.notesaver.repository.UserRepository;
import com.noteSaver.notesaver.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
    String userJson;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserService userService;
    private User user = new User();

    {
        user.setEmail("testMail@gmail.com");
        user.setUserName("tester");
        user.setPassword("password");
        user.setRole("admin");
        userJson = "{\"userName\":\"tester\",\"password\":\"password\"," +
                "\"email\":\"testMail@gmail.com\",\"role\":\"admin\"}";
    }

    @Test
    public void givenValidUserEmail_whenGetUser_thenReturnUser() throws Exception {
        when(userService.getUser("testMail@gmail.com")).thenReturn(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/users/testMail@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String expectedResult = "{\"userName\":tester,\"password\":password,\"email\":\"testMail@gmail.com\",\"role\":admin}";
        String returnedResult = mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResult, returnedResult, false);
    }

    @Test
    public void givenInvalidUserEmail_whenGetUser_thenThrowException() throws Exception {
        when(userService.getUser("invalidMail@gmail.com"))
                .thenThrow(new ResourceNotFoundException("User", "email", "invalidMail@gmail.com"));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/users/invalidMail@gmail.com")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String expectedException = "User not found with email : 'invalidMail@gmail.com'";
        Assertions.assertEquals(expectedException, Objects.requireNonNull(mvcResult.getResolvedException()).getMessage());
    }

    @Test
    public void givenUser_whenAddUser_thenReturnOkResponse() throws Exception {
        when(userService.addUser(Mockito.any(User.class))).thenReturn(user);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/notesaver/users/add")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        assertEquals(userJson, result.getResponse().getContentAsString());
    }

    @Test
    public void givenDuplicateUser_whenAddUser_thenThrowException() throws Exception {
        when(userService.addUser(Mockito.any(User.class)))
                .thenThrow(new DuplicateEntryException("User", "email", "duplicateMail@gmail.com"));
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/notesaver/users/add")
                .accept(MediaType.APPLICATION_JSON).content(userJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mvc.perform(requestBuilder).andReturn();
        String expectedException = "User already exist with email : 'duplicateMail@gmail.com'";
        assertEquals(expectedException, Objects.requireNonNull(result.getResolvedException()).getMessage());
    }

    @Test
    public void whenGetUsers_thenReturnListOfUsers() throws Exception {
        List<User> list = Arrays.asList(user);
        when(userService.getUsers()).thenReturn(list);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/notesaver/users")
                .contentType(MediaType.APPLICATION_JSON)).andReturn();
        String expectedJson = "[" + userJson + "]";
        Assertions.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        Assertions.assertEquals(expectedJson, mvcResult.getResponse().getContentAsString());
    }
}