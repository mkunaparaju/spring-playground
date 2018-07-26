package com.example.springplayground.Controller;

import com.example.springplayground.Controller.GetMathController;
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
@WebMvcTest(GetMathController.class)
public class GetMathControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAddCalculation() throws Exception {
        this.mvc.perform(get("/math/calculate/?operation=add&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }

    @Test
    public void testGetSubtractCalculation() throws Exception {
        this.mvc.perform(get("/math/calculate/?operation=subtract&x=8&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetMultiplyCalculation() throws Exception {
        this.mvc.perform(get("/math/calculate/?operation=multiply&x=4&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("24"));
    }

    @Test
    public void testGetDivideCalculation() throws Exception {
        this.mvc.perform(get("/math/calculate/?operation=divide&x=12&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    public void testGetSumCalculation() throws Exception    {
        this.mvc.perform(get("/math/sum?n=4&n=5&n=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("15"));
    }

    @Test
    public void testGetVolume() throws Exception {
        this.mvc.perform(get("/math/volume/3/4/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("60"));
    }

}