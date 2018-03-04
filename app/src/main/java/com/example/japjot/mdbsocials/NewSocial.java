package com.example.japjot.mdbsocials;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by japjot on 2/22/18.
 */

@SuppressWarnings("DefaultFileTemplate")
public class NewSocial extends AppCompatActivity implements View.OnClickListener {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference ref = database.getReference("/events");

    private EditText new_name;
    private EditText new_description;
    private EditText new_date;
    private ImageView new_image;

    private static final int PICTURE_UPLOAD = 1;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsocial);

        new_name = findViewById(R.id.editText4);
        new_description = findViewById(R.id.editText5);
        new_image = findViewById(R.id.imageView);
        new_date = findViewById(R.id.editText);
        Button create_button = findViewById(R.id.button4);

        create_button.setOnClickListener(this);
        new_image.setOnClickListener(this);

    }

    private void submit() {
        ref = FirebaseDatabase.getInstance().getReference();

        final String key = ref.child("events").push().getKey();
        StorageReference storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://mdbsocials-37345.appspot.com");
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
                String description = new_description.getText().toString();
                String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                String date = new_date.getText().toString();
//                Long timestamp = (new Date()).getTime();
                String imageURL = taskSnapshot.getDownloadUrl().toString();

                Event event = new Event(name, description, date, email, imageURL);
                ref.child("events").child(key).setValue(event);
                startActivity(new Intent(NewSocial.this, Login.class));
            }
        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                submit();
                break;

            case R.id.imageView:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICTURE_UPLOAD);
                break;
        }
    }
}