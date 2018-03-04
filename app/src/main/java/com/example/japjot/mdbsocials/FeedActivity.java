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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by japjot on 2/22/18.
 */

@SuppressWarnings("DefaultFileTemplate")
public class FeedActivity extends AppCompatActivity implements View.OnClickListener{

    private Feedadapter feedAdapter;
    private ArrayList<Event> events;

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private final DatabaseReference myRef = database.getReference("events");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        Button createEventButton = findViewById(R.id.createEventButton);
        createEventButton.setOnClickListener(this);

        Button logOut = findViewById(R.id.button2);
        logOut.setOnClickListener(this);

        RecyclerView recycleboy = findViewById(R.id.recyclerView);
        recycleboy.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        events = new ArrayList<>();
        feedAdapter = new Feedadapter(getApplicationContext(), events);
        recycleboy.setAdapter(feedAdapter);

        myRef.orderByChild("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    events.add(child.getValue(Event.class));
                }
                feedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
