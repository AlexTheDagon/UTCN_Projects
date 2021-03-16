package com.InternetCafe;

public class Reservation {

    private String date;
    private String start;
    private String end;
    private Customer customer;
    private Station station;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Reservation(String date, String start, String end, Customer customer, Station station) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.station = station;
    }
}
