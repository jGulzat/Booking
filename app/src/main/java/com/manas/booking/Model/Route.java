package com.manas.booking.Model;

public class Route {

    String from;
    String to;
    String date;
    String price;
    String arrive_time;
    String dictance;


    public Route(String  fromName, String toName, String date, String price, String arrive_time, String dictance) {
        this.from = fromName;
        this.to = toName;
        this.date = date;
        this.price = price;
        this.arrive_time = arrive_time;
        this.dictance = dictance;
    }

    public  Route(){};

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getDate() {
        return date;
    }

    public String getPrice() {
        return price;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public String getDictance() {
        return dictance;
    }

}

