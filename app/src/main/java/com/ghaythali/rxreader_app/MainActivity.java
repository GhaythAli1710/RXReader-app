package com.ghaythali.rxreader_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SNavigationDrawer sNavigationDrawer;

    private Fragment fragment = new MainFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
        Initialize();
    }
    /****/
    private void Initialize(){
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> itemList = new ArrayList<>();
        itemList.add(new MenuItem("MAIN",R.drawable.ic_scan));
        itemList.add(new MenuItem("PROFILE",R.drawable.ic_upload));
        itemList.add(new MenuItem("HISTORY",R.drawable.ic_scan));
        itemList.add(new MenuItem("SITTINGS",R.drawable.ic_launcher_foreground));

        sNavigationDrawer.setMenuItemList(itemList);
        sNavigationDrawer.setAppbarTitleTV("RXReader");
        //
        fragment = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction().replace(
                        R.id.containerMainActivityId,
                        fragment
                ).commit();
    }

}