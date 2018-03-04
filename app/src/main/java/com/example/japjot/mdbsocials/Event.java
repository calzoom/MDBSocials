package com.example.japjot.mdbsocials;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by japjot on 2/21/18.
 */

public class Event implements Serializable{

    String eventName, description, date, email, imageURL;

    int numInterested;

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getNumInterested() {
        return numInterested;
    }

    public Event(String eventName, String description, String date, String email, String imageURL, int numInterested, int rsvp) {
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.email = email;
        this.imageURL = imageURL;
        this.numInterested = numInterested;
    }

    public Event(){}

}
