package com.example.springplayground.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.Cookie;
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@WebMvcTest(FormDataController.class)
public class FormDataControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testGetRawString() throws Exception {
        String content = String.valueOf(new Random().nextInt());

        MockHttpServletRequestBuilder request = post("/string-example")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content", "hello")
                .param("author", "Dwayne");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(String.format("content=hello&author=Dwayne")));
    }

    @Test
    public void testGetMapString() throws Exception {
        MockHttpServletRequestBuilder request = post("/formMapExample")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name","larry")
                .param("age","89");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{name=larry, age=89}"));
    }

    @Test
    public void testGetObjectString() throws Exception {
        MockHttpServletRequestBuilder request = post("/formObjectExample")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name","larry")
                .param("age","89");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{name=larry, age=89}"));

    }

    @Test
    public void testGetPathFormQueryParamsString() throws Exception {
        MockHttpServletRequestBuilder request = post("/formMultiExample/34/comments?notify=email")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("content","thanks for all the fish")
                .param("name","Arthur Dent");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("id: 34 - Arthur Dent says thanks for all the fish"));

    }

    @Test
    public void testCookies() throws Exception {
        this.mvc.perform(get("/cookie").cookie(new Cookie("foo", "bar")))
                .andExpect(status().isOk())
                .andExpect(content().string("bar"));
    }
    @Test
    public void testHeaders() throws Exception {
        this.mvc.perform(get("/header").header("Host", "example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("example.com"));
    }




}