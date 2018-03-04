package com.example.japjot.mdbsocials;

import java.io.Serializable;

/**
 * Created by japjot on 2/21/18.
 */

@SuppressWarnings("DefaultFileTemplate")
class Event implements Serializable{

    String eventName;
    private String description;
    String email;
    private String imageURL;
    private String date;
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

    public void incNumInterested(){
        numInterested = numInterested + 1;
    }

    public Event(String eventName, String description, String date, String email, String imageURL) {
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.email = email;
        this.imageURL = imageURL;
        this.numInterested = 1;
    }

    public Event(){}

}
