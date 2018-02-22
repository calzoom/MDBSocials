package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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

        Button signInButton = (Button) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(this);
        Button signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpButton.setPaintFlags(signUpButton.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signInButton.setOnClickListener(this);

        ImageView logInImage = (ImageView) findViewById(R.id.logInImage);
        logInImage.setImageResource(R.drawable.loglogo);

    }

    private void trySignUp(){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
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
        if(v.getId() == R.id.signInButton){
            ProgressBar p = (ProgressBar) findViewById(R.id.progressBar4);
            p.setVisibility(ProgressBar.VISIBLE);

//            Attempt log in
            FirebaseUtils.attemptLogin(((EditText) findViewById(R.id.emailAddressText)).getText().toString(),((EditText) findViewById(R.id.passwordText)).getText().toString(),mAuth,getApplicationContext(),this);

            p.setVisibility(View.INVISIBLE);
        }

        else{
            trySignUp();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        ProgressBar p = (ProgressBar) findViewById(R.id.progressBar4);
        p.setVisibility(View.INVISIBLE);
    }

}
