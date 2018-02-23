package com.example.japjot.mdbsocials;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by japjot on 2/22/18.
 */

public class NewSocial extends AppCompatActivity{

    private static final int PICTURE_UPLOAD = 1;
    EditText new_name, new_description, new_date;
    Button create_button, back_button;
    ImageView new_image;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("/socials");
    private StorageReference storageRef;

    private static FirebaseAuth mAuth;
    private static FirebaseUser mUser;

    Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsocial);

        new_name = findViewById(R.id.editText4);
        new_description = findViewById(R.id.editText5);
        new_date = findViewById(R.id.editText2);
        new_image = findViewById(R.id.imageView);
        create_button = findViewById(R.id.button4);
//        back_button = findViewById(R.id.back_button);
        View.OnFocusChangeListener keyboardHider = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        };

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        new_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICTURE_UPLOAD);
            }
        });

//        back_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AddSocialActivity.this, MainActivity.class));
//            }
//        });
        new_name.setOnFocusChangeListener(keyboardHider);
        new_date.setOnFocusChangeListener(keyboardHider);
        new_description.setOnFocusChangeListener(keyboardHider);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            Log.d("AddSocialActivity", "????");
        }

        if (requestCode == PICTURE_UPLOAD && resultCode == RESULT_OK) {
            selectedImageUri = data.getData();
            if (null != selectedImageUri) {
                String path = selectedImageUri.getPath();
                Log.e("image path", path + "");
                new_image.setImageURI(selectedImageUri);
            }
        }
    }

    public void submit() {
        ref = FirebaseDatabase.getInstance().getReference();

        final String key = ref.child("socials").push().getKey();
        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://mdbsocials-37345.appspot.com");
        StorageReference socialsRef = storageRef.child(key + ".png");

        if (selectedImageUri == null) {
            Log.d("SUBMIT", "image null");
        }
        socialsRef.putFile(selectedImageUri).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewSocial.this, "Cannot upload file into storage.", Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String name = new_name.getText().toString();
                String date = new_date.getText().toString();
                String description = new_description.getText().toString();
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//                Long timestamp = (new Date()).getTime();
                String imageURL = taskSnapshot.getDownloadUrl().toString();

                Events social = new Events(name, description, date, email, imageURL,"five");
                ref.child("socials").child(key).setValue(social);
                startActivity(new Intent(NewSocial.this, Login.class));
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}