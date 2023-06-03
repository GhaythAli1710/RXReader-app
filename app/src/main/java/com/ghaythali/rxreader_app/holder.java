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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class holder extends AppCompatActivity {

    private ImageView imgVeiw;
    private Button openCam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);

        imgVeiw = findViewById(R.id.capturedImage);
        openCam = findViewById(R.id.openCam);

        openCam.setOnClickListener(v -> {
            Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(open_camera, 100);
            testAPI();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        imgVeiw.setImageBitmap(photo);
    }

    private void testAPI(){
        //
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://192.168.137.153:8000/test/",
                response -> Toast.makeText(holder.this, response.toString(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(holder.this, error.toString(), Toast.LENGTH_SHORT).show()
        );
        queue.add(stringRequest);
    }
}