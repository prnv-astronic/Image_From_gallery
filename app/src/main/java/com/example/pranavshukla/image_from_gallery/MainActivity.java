package com.example.pranavshukla.image_from_gallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private int PICK_IMAGE_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // register button
        Button open_butt=(Button)findViewById(R.id.button);
        // set on click listner for button

        open_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_image =new Intent();
                open_image.setType("image/*");
                open_image.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(open_image, "Select Picture"), PICK_IMAGE_REQUEST);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();
            Bitmap image = null;
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
            } catch (IOException v) {
                v.printStackTrace();
            }

            ImageView imageView = (ImageView) findViewById(R.id.imageView2);
            imageView.setImageBitmap(image);


        }
    }


}
