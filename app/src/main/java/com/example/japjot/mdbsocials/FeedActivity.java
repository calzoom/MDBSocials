package com.example.japjot.mdbsocials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.ArrayList;

/**
 * Created by japjot on 2/22/18.
 */

public class FeedActivity extends AppCompatActivity{

    private RecyclerView recycleboy;

    private Feedadapter feedAdapter;
    private ArrayList<Eventsclass.Event> events;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        recycleboy = (RecyclerView)findViewById(R.id.recyclerView);
        recycleboy.setHasFixedSize(true);
        recycleboy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Eventsclass eventsclass = new Eventsclass();
        events = eventsclass.getEvent();
        updateRecyclerView(events);
    }

    public void updateRecyclerView(ArrayList<Eventsclass.Event> p){
        recycleboy.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new Feedadapter(getApplicationContext(), p);
        recycleboy.setAdapter(feedAdapter);
    }

}
