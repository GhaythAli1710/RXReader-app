package com.ghaythali.rxreader_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class RXProcessingActivity extends AppCompatActivity {

    private Fragment fragment = new InsertPhotoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        Initialize();
    }
    /*****/
    @SuppressLint("MissingInflatedId")
    private void Initialize(){
        setContentView(R.layout.activity_rxprocessing);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /**/
        fragment = new InsertPhotoFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(
                        R.id.containerRXProcessingActivityId,
                        fragment
                ).commit();

    }
}