package com.ghaythali.rxreader_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class holder extends AppCompatActivity {

    private ImageView imgVeiw;
    private Button openCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);

        imgVeiw = findViewById(R.id.capturedImage);
        openCam = findViewById(R.id.openCam);

        openCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(open_camera, 100);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imgVeiw.setImageBitmap(photo);
    }
}