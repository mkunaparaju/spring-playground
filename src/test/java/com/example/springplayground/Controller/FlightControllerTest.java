package com.example.springplayground.Controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightControllerTest {

    @Autowired
    MockMvc mvc;

    @Ignore
    @Test
    public void testGetSingleFlight(){

    }

    @Ignore
    @Test
    public void testGetAllFlights(){

    }


}