package com.example.springplayground.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

   // @JsonProperty("Passenger")
    private Passenger passenger;

   // @JsonProperty("Price")
    private int price;

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "passenger=" + passenger +
                ", price=" + price +
                '}';
    }
}
