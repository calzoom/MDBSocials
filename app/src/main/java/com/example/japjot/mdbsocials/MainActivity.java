package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static String email;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button signInButton = (Button) findViewById(R.id.signInB);
        signInButton.setOnClickListener(this);

        Button signUpButton = (Button) findViewById(R.id.signUpB);
        signUpButton.setOnClickListener(this);

        ImageView logInImage = (ImageView) findViewById(R.id.logInImage);
        logInImage.setImageResource(R.drawable.loglogo);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    Log.d("Login Status", "onAuthStateChanged:signed_in:" + user.getUid());
                    MainActivity.email = user.getEmail();

                    Intent intent = new Intent(getApplicationContext(),FeedActivity.class);
                    startActivity(intent);

                } else {
                    // User is signed out
                    Log.d("Login Status", "onAuthStateChanged:signed_out");
                }

            }
        };

    }

    private void trySignUp(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
//        setContentView(R.layout.activity_register);
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

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.signUpB){
            trySignUp();
        }

        else if(v.getId() == R.id.signInB){

            ProgressBar p = (ProgressBar) findViewById(R.id.progressBar4);
            p.setVisibility(ProgressBar.VISIBLE);
            FirebaseUtils.attemptLogin(((EditText) findViewById(R.id.emailAddressText)).getText().toString(),((EditText) findViewById(R.id.passwordText)).getText().toString(),mAuth,getApplicationContext(),this);
            p.setVisibility(View.INVISIBLE);

        }

    }

    @Override
    protected void onResume(){
        super.onResume();
        ProgressBar p = (ProgressBar) findViewById(R.id.progressBar4);
        p.setVisibility(View.INVISIBLE);
    }

}
