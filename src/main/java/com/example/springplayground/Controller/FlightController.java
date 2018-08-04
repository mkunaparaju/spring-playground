package com.example.springplayground.Controller;

import com.example.springplayground.Model.Flight;
import com.example.springplayground.Model.Passenger;
import com.example.springplayground.Model.Ticket;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private Flight flightOne = new Flight();
    private Flight flightTwo = new Flight();
    private List<Flight> flights = new ArrayList<Flight>();

    public Flight getFlightOne() throws ParseException {
        Passenger passenger = new Passenger();
        passenger.setFirstName("Some name");
        passenger.setLastName("Some other name");

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(200);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date departs = sdf.parse("2017-04-21 14:34");

        flightOne.setDeparts(departs);
        flightOne.setTickets(Collections.singletonList(ticket));
        return flightOne;
    }

    public Flight getFlightTwo() throws ParseException {

        Passenger passenger = new Passenger();
        passenger.setFirstName("Some other name");

        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        ticket.setPrice(400);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date departs = sdf.parse("2017-04-21 14:34");

        flightTwo.setDeparts(departs);
        flightTwo.setTickets(Collections.singletonList(ticket));
        return flightTwo;
    }

    @GetMapping("/flight")
    public Flight getFlight() throws ParseException {
        return getFlightOne();
    }

    @GetMapping("")
    public List<Flight> getFlights() throws ParseException {
        flights.add(getFlightOne());
        flights.add(getFlightTwo());
        return flights;
    }



}
