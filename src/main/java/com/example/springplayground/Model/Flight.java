package com.example.springplayground.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.sql.DataSourceDefinition;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Flight {

    @JsonFormat( pattern = "yyyy-MM-dd HH:mm")
   // @JsonProperty("Departs")
    private Date departs;

   // @JsonProperty("Tickets")
    private List<Ticket> tickets;

    public Date getDeparts() {
        return departs;
    }

    public void setDeparts(Date departs) {
        this.departs = departs;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


    @Override
    public String toString() {
        return "Flight{" +
                "departs=" + departs +
                ", tickets=" + tickets +
                '}';
    }
}
