package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("ye", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("ye", "onAuthStateChanged:signed_out");
                }
            }
        };

        Button signInButton = findViewById(R.id.signInB);
        signInButton.setOnClickListener(this);

        Button signUpButton = findViewById(R.id.signUpB);
        signUpButton.setOnClickListener(this);

        ImageView logInImage = findViewById(R.id.logInImage);
        logInImage.setImageResource(R.drawable.loglogo);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);

    }

    private void attemptLogin() {
        String email = ((EditText) findViewById(R.id.emailAddressText)).getText().toString();
        String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();
        if (!email.equals("") && !password.equals("")) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("ye", "signInWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w("ye", "signInWithEmail:failed", task.getException());
                                Toast.makeText(Login.this, "Sign in failed",
                                        Toast.LENGTH_SHORT).show();
                            }

                            else {
                                Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }

    private void attemptSignup() {
        String email = ((EditText) findViewById(R.id.emailAddressText)).getText().toString();
        final String password = ((EditText) findViewById(R.id.passwordText)).getText().toString();

        if(password.length() < 6){
            Toast.makeText(Login.this, "Password should be at least 6 characters!",
                    Toast.LENGTH_SHORT).show();
        }

        if (!email.equals("") && !password.equals("")) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d("ye", "createUserWithEmail:onComplete:" + task.isSuccessful());

                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if(password.length() < 6){
                                Toast.makeText(Login.this, "Password should be at least 6 characters!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else if (!task.isSuccessful()) {
                                Log.w("ye", "signInWithEmail:failed", task.getException());
                                Toast.makeText(Login.this, "Sign up failed",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }
    }

    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.signInB)){
            attemptLogin();
        }

        else if(v == findViewById(R.id.signUpB)){
            attemptSignup();
        }

    }
}
