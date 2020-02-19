package com.manas.booking;

public class Stations {
   int pk;
    String name;
    String address;

    public Stations(int pk, String name, String address) {
        this.pk = pk;
        this.name = name;
        this.address = address;
    }
    public  Stations(){}

    public int getPk() {
        return pk;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

}
