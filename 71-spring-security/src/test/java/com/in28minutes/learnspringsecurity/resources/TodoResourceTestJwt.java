package com.in28minutes.learnspringsecurity.resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in28minutes.learnspringsecurity.jwt.JwtSecurityConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(TodoResource.class)
@Import(JwtSecurityConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoResourceTestJwt {

   @Autowired
    MockMvc mockMvc;

   @Autowired
    ObjectMapper objectMapper;

    //Create a JWT for test purpose
    private SecurityMockMvcRequestPostProcessors.JwtRequestPostProcessor jwtRequestPostProcessor = jwt();

    //use a test JWT for the authentication
    @Test
    void retrieveAllTodos() throws Exception{
        MvcResult mvcResult= mockMvc.perform(get("/todos")
                        .with(jwtRequestPostProcessor))
                .andExpect(status().isOk()).andReturn();
        String result =  mvcResult.getResponse().getContentAsString();

        List< Todo> todos = objectMapper.readValue(result, List.class);
        assertTrue(todos.size()==2);

    }

    @Test
    void retrieveTodosforUser() throws Exception{
        MvcResult mvcResult= mockMvc.perform(get("/users/in28minutes/todos")
                        .with(jwtRequestPostProcessor))
                .andExpect(status().isOk()).andReturn();
        String result =  mvcResult.getResponse().getContentAsString();

        Todo todo  = objectMapper.readValue(result, Todo.class);
        assertTrue(todo.username().equals("in28minutes"));
        assertTrue(todo.description().equals("Learn AWS"));

    }

    @Test
    void postTodosforUser() throws Exception{
        Todo todo = new Todo("in28minutes", "Learn Java");
        mockMvc.perform(post("/users/in28minutes/todos")
                        .with(jwtRequestPostProcessor)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().is2xxSuccessful());
    }

    //Without the JWT, user will not be authorized.
    @Test
    void postTodosforUnAuthUser() throws Exception {
        Todo todo = new Todo("in28minutes", "Learn Java");
        mockMvc.perform(post("/users/in28minutes/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isUnauthorized());
   }
}
