package com.example.springplayground.Controller;

import com.example.springplayground.Model.Passenger;
import com.example.springplayground.Model.Ticket;
import com.example.springplayground.Model.TicketsTotalRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
@AutoConfigureMockMvc(secure=false)
public class FlightControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testGetSingleFlight() throws Exception {
        this.mvc.perform(get("/flights/flight")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$.tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$.tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$.tickets[0].price", is(200)));
    }

    @Test
    public void testGetAllFlights() throws Exception {
        this.mvc.perform(get("/flights")
                            .accept(MediaType.APPLICATION_JSON_UTF8)
                            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.firstName", is("Some name")))
                .andExpect(jsonPath("$[0].tickets[0].passenger.lastName", is("Some other name")))
                .andExpect(jsonPath("$[0].tickets[0].price", is(200)))
                .andExpect(jsonPath("$[1].departs", is("2017-04-21 14:34")))
                .andExpect(jsonPath("$[1].tickets[0].passenger.firstName", is("Some other name")))
                .andExpect(jsonPath("$[1].tickets[0].price", is(400)));
    }

    @Test
    public void testGetTotal() throws Exception {
        String json = getJSON("/TicketTotalRequest.json");
        String responseJson = getJSON("/TicketTotalResponse.json");

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json(responseJson));
    }

    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }

    @Test
    public void testGetFligthsTotal_whenString_thenReurnExpected() throws Exception {
        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tickets\": [{\"passenger\": {\"firstName\": \"Some name\",\"lastName\": \"Some other name\"},\"price\": 200},{\"passenger\": {\"firstName\": \"Name B\",\"lastName\": \"Name C\"},\"price\": 150}]}");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }

    @Test
    public void testGetFligthsTotal_whenSerialization_thenReturnExpected() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        Passenger passOne = new Passenger();
        passOne.setFirstName("Some name");
        passOne.setLastName("Some other name");

        Passenger passTwo = new Passenger();
        passOne.setFirstName("Name B");
        passOne.setLastName("Name C");

        Ticket ticketOne = new Ticket();
        ticketOne.setPassenger(passOne);
        ticketOne.setPrice(200);

        Ticket ticketTwo = new Ticket();
        ticketTwo.setPassenger(passTwo);
        ticketTwo.setPrice(150);

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticketOne);
        tickets.add(ticketTwo);

        TicketsTotalRequest requestTickets = new TicketsTotalRequest();
        requestTickets.setTickets(tickets);

        String json = objectMapper.writeValueAsString(requestTickets);

        MockHttpServletRequestBuilder request = post("/flights/tickets/total")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":350}"));
    }


}