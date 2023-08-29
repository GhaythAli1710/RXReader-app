package com.ghaythali.rxreader_app;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.theartofdev.edmodo.cropper.CropImage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PreviewRXFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PreviewRXFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Uri imageUri;

    public PreviewRXFragment() {
        // Required empty public constructor
    }

    public PreviewRXFragment(Uri uri) {
        // Required empty public constructor
        imageUri = uri;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PreviewRXFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PreviewRXFragment newInstance(String param1, String param2) {
        PreviewRXFragment fragment = new PreviewRXFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_preview_r_x, container, false);
        ImageView rxImageView = view.findViewById(R.id.rxImageViewId);
        rxImageView.setImageURI(imageUri);
        //
        Button selectMedBtn = view.findViewById(R.id.selectMedBtnId);
        selectMedBtn.setOnClickListener(view1 -> getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerRXProcessingActivityId, new CroperFragment(imageUri))
                .addToBackStack(null)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit());
        return view;
    }
}