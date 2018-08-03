package com.example.springplayground.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(JSONResponseController.class)

public class JSONResponseControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetJsonObject() throws Exception {
        this.mvc.perform(get("/json/object")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Dwayne")))
                .andExpect(jsonPath("$.age", is("12")));
    }

    @Test
    public void testGetJsonArray() throws Exception {
        this.mvc.perform(
                get("/json/object-array")
                        .accept(MediaType.APPLICATION_JSON_UTF8)
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Dwayne")))
                .andExpect(jsonPath("$[0].age", is("12")));
    }
}