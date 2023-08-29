package com.ghaythali.rxreader_app;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CroperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CroperFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /****/
    Uri imageUri;

    ImageView previewMed;
    private Bitmap bitmap;

    public CroperFragment(Uri uri) {
        // Required empty public constructor
        imageUri = uri;
    }

    public CroperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CroperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CroperFragment newInstance(String param1, String param2) {
        CroperFragment fragment = new CroperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_croper, container, false);
        previewMed = view.findViewById(R.id.previewMedId);
        Button submetBtn = view.findViewById(R.id.submetBtnId);
        //
        CropImage.activity(imageUri)
                .start(getContext(), this);
        //
        submetBtn.setOnClickListener(view1 -> testAPI());
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();
                previewMed.setImageURI(resultUri);
                BitmapDrawable drawable = (BitmapDrawable) previewMed.getDrawable();
                bitmap = drawable.getBitmap();
                Log.d("gh", ""+ bitmap);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    //
    private void testAPI(){
        //
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.137.191:8000/receive-image/",
                response -> Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_SHORT).show(),
                error -> Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show()
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("image", encodeBitmapImage());
                return params;
            }
        };
        queue.add(stringRequest);
    }
    //
    private String encodeBitmapImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        String encodeImageString = Base64.encodeToString(bytesofimage, Base64.DEFAULT);
        return encodeImageString;
    }
}