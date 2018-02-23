package com.example.japjot.mdbsocials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by japjot on 2/21/18.
 */

public class Events {
        String eventName, description, date, email, imageURL, numInterested;

    public Events(String eventName, String description, String date, String email, String imageURL, String numInterested) {
        this.eventName = eventName;
        this.description = description;
        this.date = date;
        this.email = email;
        this.imageURL = imageURL;
        this.numInterested = numInterested;
    }

}
