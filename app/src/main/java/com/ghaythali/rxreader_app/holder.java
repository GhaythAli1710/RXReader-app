package com.ghaythali.rxreader_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import android.util.Base64;

public class holder extends AppCompatActivity {

    private ImageView imgVeiw;
    private Button openCam, upload;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holder);

        imgVeiw = findViewById(R.id.capturedImage);
        openCam = findViewById(R.id.openCam);
        upload = findViewById(R.id.upload);

        openCam.setOnClickListener(v -> {
//            Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            startActivityForResult(open_camera, 100);
            Log.d("gherror", "000");

            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON).start(holder.this);
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testAPI();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        bitmap = (Bitmap) data.getExtras().get("data");
//        imgVeiw.setImageBitmap(bitmap);
        Log.d("gherror", "111");

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                Log.d("gherror", resultUri.toString());
//                imgVeiw.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void testAPI(){
        //
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.1.6:8000/test2/",
                response -> Toast.makeText(holder.this, response.toString(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(holder.this, error.toString(), Toast.LENGTH_SHORT).show()
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("image", encodeBitmapImage(bitmap));
                return params;
            }
        };
        queue.add(stringRequest);
    }

    //
    private String encodeBitmapImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        String encodeImageString = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
        return encodeImageString;
    }

}