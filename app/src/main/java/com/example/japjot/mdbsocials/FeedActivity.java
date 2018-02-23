package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by japjot on 2/22/18.
 */

public class FeedActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recycleboy;

    private Feedadapter feedAdapter;
//    private ArrayList<Events.Event> events;
    private ArrayList<Events> events;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Button createEventButton = (Button) findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(this);

        Button logOut = (Button) findViewById(R.id.button2);
        logOut.setOnClickListener(this);

        recycleboy = (RecyclerView)findViewById(R.id.recyclerView);
        recycleboy.setHasFixedSize(true);
        recycleboy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Events events = new Events();
        this.events = events.getEvent();
        updateRecyclerView(this.events);
    }

    public void updateRecyclerView(ArrayList<Events.Event> p){
        recycleboy.setLayoutManager(new LinearLayoutManager(this));
        feedAdapter = new Feedadapter(getApplicationContext(), p);
        recycleboy.setAdapter(feedAdapter);
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.createEventButton)){
            // create event page
            Intent intent = new Intent(getApplicationContext(), NewSocial.class);
            startActivity(intent);
        }
        else if(v == findViewById(R.id.button2)){
            Toast.makeText(this, "Logged Out", Toast.LENGTH_SHORT).show();
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }
}
