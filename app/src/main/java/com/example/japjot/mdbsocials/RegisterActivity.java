package com.example.japjot.mdbsocials;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by japjot on 2/21/18.
 */

@SuppressWarnings("DefaultFileTemplate")
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private static FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        FirebaseAuth.AuthStateListener mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("ye", "onAuthStateChanged:signed_in:" + user.getUid());

                } else {
                    Log.d("ye", "onAuthStateChanged:signed_out");
                }
            }
        };

        Button register = findViewById(R.id.createAccountButton);
        register.setOnClickListener(this);
    }

    private void attemptSignup(String email, String password) {
//        String email = ((EditText) findViewById(R.id.registerEmailText)).getText().toString();
//        String password = ((EditText) findViewById(R.id.registerPasswordText)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("ye", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("ye", "signInWithEmail:failed", task.getException());
                            Toast.makeText(RegisterActivity.this, "sign up failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
//                            Intent intent = new Intent(getApplicationContext(), FeedActivity.class);
//                            startActivity(intent);
                        }
                    }
                });
    }

    public void onClick(View view) {
        if (view.getId() == R.id.createAccountButton) {
            attemptSignup(((EditText) findViewById(R.id.registerEmailText)).getText().toString(), ((EditText) findViewById(R.id.registerPasswordText)).getText().toString());
        }
    }

}
