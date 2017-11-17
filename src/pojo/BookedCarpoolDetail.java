package pojo;


import util.Pair;

import java.util.ArrayList;

/**
 * Created by chenjunhao on 2017/11/15.
 */
public class BookedCarpoolDetail {
    private String departure;
    private String destination;
    private String date;
    private ArrayList<Pair<String,String>> users;

    public BookedCarpoolDetail(String departure, String destination, String date, ArrayList<Pair<String, String>> users) {
        this.departure = departure;
        this.destination = destination;
        this.date = date;
        this.users = users;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<Pair<String, String>> getUsers() {
        return users;
    }
}
