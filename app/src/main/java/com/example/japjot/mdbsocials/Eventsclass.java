package com.example.japjot.mdbsocials;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by japjot on 2/21/18.
 */

public class Eventsclass {

    public ArrayList<Event> getEvent(){
        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("event1", "desc1", "date1", "email1", "imageUrl1", "numInterest1", "key1"));
        events.add(new Event("event2", "desc2", "date2", "email2", "imageUrl2", "numInterest2", "key2"));
        events.add(new Event("event3", "desc3", "date3", "email3", "imageUrl3", "numInterest3", "key3"));
        events.add(new Event("event4", "desc4", "date4", "email4", "imageUrl4", "numInterest4", "key4"));
        events.add(new Event("event5", "desc5", "date5", "email5", "imageUrl5", "numInterest5", "key5"));
        events.add(new Event("event6", "desc6", "date6", "email6", "imageUrl6", "numInterest6", "key6"));
        events.add(new Event("event7", "desc7", "date7", "email7", "imageUrl7", "numInterest7", "key7"));
        events.add(new Event("event8", "desc8", "date8", "email8", "imageUrl8", "numInterest8", "key8"));
        events.add(new Event("event9", "desc9", "date9", "email9", "imageUrl9", "numInterest9", "key9"));

        return events;
    }

    public class Event {
        String eventName, description, date, email, imageURL, numInterested, key;

        public Event(String eventName, String description, String date, String email, String imageURL, String numInterested, String key) {
            this.eventName = eventName;
            this.description = description;
            this.date = date;
            this.email = email;
            this.imageURL = imageURL;
            this.numInterested = numInterested;
            this.key = key;
        }
    }

}
