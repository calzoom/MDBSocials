package com.example.japjot.mdbsocials;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by japjot on 2/22/18.
 */

public class NewSocial extends AppCompatActivity implements View.OnClickListener {

    public static final int GET_FROM_GALLERY = 3;
    public static final int GET_FROM_CAMERA = 4;
    public Uri currentImage = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsocial);

        Button chooseFile = (Button) findViewById(R.id.button3);
        chooseFile.setOnClickListener(this);
    }

    @SuppressLint("WrongViewCast")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {



            Uri selectedImage = data.getData();
            currentImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                ((ImageButton) findViewById(R.id.button3)).setBackgroundDrawable(bitmapDrawable);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if (requestCode == GET_FROM_CAMERA) {

            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                //BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
                ((ImageView) findViewById(R.id.button3)).setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
    @Override
    public void onClick(View v) {
        if(v == findViewById(R.id.button3)){
            AlertDialog alertDialog = new AlertDialog.Builder(NewSocial.this).create();
            alertDialog.setTitle("Set a Photo");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Take a Photo",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //Open Camera
                            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                                startActivityForResult(takePictureIntent, GET_FROM_CAMERA);
                            }

                            dialog.dismiss();
                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Upload from Gallery",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //launch gallery
                            dialog.dismiss();
                            startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
                        }
                    });
            alertDialog.show();
        }
    }
}
