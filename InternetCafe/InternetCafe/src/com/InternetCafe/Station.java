package com.InternetCafe;

public class Station {
    String type;
    int ID;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Station(String type, int ID) {
        this.type = type;
        this.ID = ID;
    }
}
