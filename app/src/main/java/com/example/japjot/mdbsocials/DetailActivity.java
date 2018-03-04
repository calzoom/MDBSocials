package com.example.japjot.mdbsocials;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

/**
 * Created by japjot on 2/22/18.
 */

public class DetailActivity extends AppCompatActivity{

    TextView name;
    TextView email;
    TextView description;
    TextView rsvp;
    ImageView img;
    Button interested;
    boolean rsvpd = false;
    private static FirebaseAuth mAuth;

//    event.id = getIntent().getStringExtra("events id");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.textView);
        email = findViewById(R.id.textView10);
        description = findViewById(R.id.textView7);
        rsvp = findViewById(R.id.textView6);
        img = findViewById(R.id.detailImageView);
        interested = findViewById(R.id.button3);
        mAuth = FirebaseAuth.getInstance();

        final Event event = (Event) getIntent().getSerializableExtra("Event");

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                event.incNumInterested(1);
//                transaction();
            }
        });

        setInfo(event);

    }

//    public void transaction() {
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
//        String id = Login.mAuth.getCurrentUser().getUid();
//        final DatabaseReference newRef = ref.child("users").child(id).child("interestedList");
////        final String socialid = social.id;
////        newRef.child(social.id).setValue(social.id);
////        ref.child("socials").child(social.id).runTransaction(new Transaction.Handler() {
//            @Override
//            public Transaction.Result doTransaction(MutableData mutableData) {
//                Message social = mutableData.getValue(Message.class);
//                if (rsvpd) {
//                    event.numInterested = event.numInterested - 1;
//                    newRef.child(socialid).setValue(null);
//                    rsvpd = false;
//                } else {
//                    event.numInterested = event.numInterested + 1;
//                    newRef.child(socialid).setValue(socialid);
//                    rsvpd = true;
//                }
//                mutableData.setValue(social);
//                return Transaction.success(mutableData);
//            }
//
//            @Override
//            public void onComplete(DatabaseError databaseError, boolean b,
//                                   DataSnapshot dataSnapshot) {
//                Log.d("bug", "postTransaction:onComplete:" + databaseError);
//            }
//        });
//    }

    public void setInfo(Event ev){
        this.name.setText(ev.getEventName());
        this.email.setText(ev.getEmail());
        this.description.setText(ev.getDescription());
        this.rsvp.setText(Integer.toString(ev.getNumInterested()));
        Glide.with(this).load(ev.getImageURL()).into(img);
    }
}
