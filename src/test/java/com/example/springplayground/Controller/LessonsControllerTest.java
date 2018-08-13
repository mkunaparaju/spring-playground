package com.example.springplayground.Controller;

import com.example.springplayground.Model.Lesson;
import com.example.springplayground.crud.LessonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LessonsControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    LessonRepository repository;

    Lesson lesson;
    Lesson lesson2;

    @Before
    public void setUp() throws ParseException {
        lesson = new Lesson();
        lesson.setTitle("testing GET");

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf1.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date deliveredOn = sdf1.parse("2017-04-21 14:34");
        lesson.setDeliveredOn(deliveredOn);
        repository.save(lesson);

        lesson2 = new Lesson();
        lesson2.setTitle("testing POST");
        lesson2.setDeliveredOn(deliveredOn);
        repository.save(lesson2);

    }

    @Test
    @Transactional
    @Rollback
    public void testGet() throws Exception {
        MockHttpServletRequestBuilder request = get("/lessons")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].title", Is.is("testing GET")));

    }

    @Test
    @Transactional
    @Rollback
    public void testPost() throws Exception{
        ObjectMapper mapper = new ObjectMapper();

        MockHttpServletRequestBuilder request = post("/lessons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(lesson2));

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Is.is("testing POST")));


    }


}