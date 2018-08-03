package com.example.springplayground.Model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class Flight {

    private int id;
    private String destination;
    private Date departsOn;
    private List<Person> passengers;
    private Person pilot;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDepartsOn() {
        return departsOn;
    }

    public void setDepartsOn(Date departsOn) {
        this.departsOn = departsOn;
    }

    public List<Person> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Person> passengers) {
        this.passengers = passengers;
    }

    public Person getPilot() {
        return pilot;
    }

    public void setPilot(Person pilot) {
        this.pilot = pilot;
    }
}
