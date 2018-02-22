package com.example.japjot.mdbsocials;

import java.util.ArrayList;

/**
 * Created by japjot on 2/21/18.
 */

public class Event {

    String eventName, description, date, email, imageURL, numInterested, key;

    ArrayList<String> peopleInterested;

    public Event(){
        peopleInterested = new ArrayList();
    }

}
