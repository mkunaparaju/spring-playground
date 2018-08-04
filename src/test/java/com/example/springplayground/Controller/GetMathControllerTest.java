package com.example.springplayground.Controller;

import com.example.springplayground.Controller.GetMathController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void testGetDefaultCalculation() throws Exception {
        this.mvc.perform(get("/math/calculate/?x=12&y=6"))
                .andExpect(status().isOk())
                .andExpect(content().string("18"));
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

    @Test
    public void testGetCircleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","circle")
                .param("radius","4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));

    }

    @Test
    public void testGetRectangleArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type","rectangle")
                .param("height","7")
                .param("width","4");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Area of a 4x7 rectangle is 28"));

    }

    @Test
    public void testGetInvalidArea() throws Exception {
        MockHttpServletRequestBuilder request = post("/math/area")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("type", "rectangle")
                .param("radius", "5");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("INVALID"));

    }



}