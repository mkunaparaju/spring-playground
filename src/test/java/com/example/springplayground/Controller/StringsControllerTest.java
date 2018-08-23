package com.example.springplayground.Controller;

import com.example.springplayground.Service.WordCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StringsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WordCounter wordCounter;


    @Test
    public void wordCount_returnsCorrectResponse() throws Exception{
        Map<String, Integer> wordCount = new HashMap<>();
        wordCount.put("the", 2);

        when(wordCounter.count(any())).thenReturn(wordCount);

        MockHttpServletRequestBuilder request = post("/words/count")
                .contentType(MediaType.APPLICATION_JSON)
                .content("The the");

        this.mvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk());

    }
}