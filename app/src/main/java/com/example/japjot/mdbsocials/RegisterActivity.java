package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by japjot on 2/21/18.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //set up onclick listeners for buttons
        Button register = (Button) findViewById(R.id.createAccountButton);
        register.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Register Status", "onAuthStateChanged:signed_in:" + user.getUid());
                    MainActivity.email = user.getEmail();

                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);


                } else {
                    // User is signed out
                    Log.d("Register Status", "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.createAccountButton) {
            FirebaseUtils.attemptRegister(((EditText) findViewById(R.id.registerEmailText)).getText().toString(),((EditText) findViewById(R.id.registerPasswordText)).getText().toString(),mAuth,getApplicationContext(),this);
        }
    }

}
