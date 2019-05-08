package com.noteSaver.notesaver.controller;

import com.noteSaver.notesaver.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void givenUser_whenGetUserWithEmail_thenReturnUser() throws Exception{
        User user = new User();
        user.setEmail("testMail@gmail.com");
        when(userController.getUserByEmail("testMail@gmail.com")).thenReturn(user);
       MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.get("/notesaver/users/testMail@gmail.com")
        .contentType(MediaType.APPLICATION_JSON)).andReturn();
       String expectedResult="{\"userName\":null,\"password\":null,\"email\":\"testMail@gmail.com\",\"role\":null}";
       String returnedResult =mvcResult.getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResult,returnedResult,false);
    }
}
