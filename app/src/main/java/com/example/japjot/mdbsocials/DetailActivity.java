package com.example.japjot.mdbsocials;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by japjot on 2/22/18.
 */

public class DetailActivity extends AppCompatActivity{

    TextView name;
    TextView email;
    TextView description;
    TextView rsvp;
    ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Event event = (Event) getIntent().getSerializableExtra("Event");

        name = findViewById(R.id.textView);
        email = findViewById(R.id.textView10);
        description = findViewById(R.id.textView7);
        rsvp = findViewById(R.id.textView6);
        img = findViewById(R.id.detailImageView);

        setInfo(event);
    }

    public void setInfo(Event ev){
        this.name.setText(ev.getEventName());
        this.email.setText(ev.getEmail());
        this.description.setText(ev.getDescription());
        this.rsvp.setText(Integer.toString(ev.getNumInterested()));
        Glide.with(this).load(ev.getImageURL()).into(img);
    }
}
