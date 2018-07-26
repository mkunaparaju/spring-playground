package com.example.springplayground.Controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GetPathVariablesController.class)
public class GetPathVariablesControllerTest {

    @Autowired
    MockMvc mcv;

    @Test
    public void testGetPathVariable() throws Exception {
        int q = 4; // in real life you might pull this from a database...
        String from = "here";
        this.mcv.perform(get(String.format("/individual-example/%d/%s", q, from)))
                .andExpect(status().isOk())
                .andExpect(content().string("q:4 from:here"));
    }

    @Test
    public void testGetCommentsForTask() throws Exception {
        this.mcv.perform(get(String.format("/tasks/1/comments/2")))
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 1; commentId is 2"));
    }

    @Test
    public void testGetCommentsForTaskWithMap() throws Exception {
        this.mcv.perform(get(String.format("/test/tasks/1/comments/2")))
                .andExpect(status().isOk())
                .andExpect(content().string("{taskId=1, commentId=2}"));
    }

    @Test
    public void testGetCommentsForTaskWithObject() throws Exception {
        this.mcv.perform(get(String.format("/object/tasks/1/comments/2")))
                .andExpect(status().isOk())
                .andExpect(content().string("taskId is 1; commentId is 2"));

    }


}