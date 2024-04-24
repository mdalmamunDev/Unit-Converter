package com.example.unitconverterBy_Al_Mamun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    LinearLayout lengthBtn, areaBtn, volumeBtn, weightBtn, tempBtn, speedBtn, currencyBtn, powerBtn, pressureBtn;
    ImageView mainInfoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawerLay);
        navigationView = findViewById(R.id.navigationView);
//        toolbar = findViewById(R.id.toolBar);

//        setSupportActionBar(toolbar);

//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
//
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_about){
                    Gadgets.openActivity(MainActivity.this, Information.class);
                } else if (id == R.id.nav_contact) {
                    Gadgets.openActivity(MainActivity.this, Gadgets.MY_WEB_LINK);
                } else if (id == R.id.nav_exit) {
                    onBackPressed();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });




        assignBtn(lengthBtn, R.id.lengthBtn);
        assignBtn(areaBtn, R.id.areaBtn);
        assignBtn(volumeBtn, R.id.volumeBtn);
        assignBtn(weightBtn, R.id.weighttBtn);
        assignBtn(tempBtn, R.id.tempBtn);
        assignBtn(speedBtn, R.id.speedBtn);
        assignBtn(mainInfoBtn, R.id.main_info_btn);
        assignBtn(currencyBtn, R.id.currencyBtn);
        assignBtn(powerBtn, R.id.powerBtn);
        assignBtn(pressureBtn, R.id.pressureBtn);
    }

    void assignBtn(LinearLayout btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }
    void assignBtn(ImageView btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.main_info_btn) {
            Gadgets.openActivity(MainActivity.this, Information.class, "info_from", "main");
        } else {
            LinearLayout currTarget = (LinearLayout) view;
            String activityWillOpen;
            switch (currTarget.getId()) {
                case (R.id.lengthBtn):
                    activityWillOpen = "Length";
                    break;
                case (R.id.areaBtn):
                    activityWillOpen = "Area";
                    break;
                case (R.id.volumeBtn):
                    activityWillOpen = "Volume";
                    break;
                case (R.id.weighttBtn):
                    activityWillOpen = "Weight";
                    break;
                case (R.id.tempBtn):
                    activityWillOpen = "Temperature";
                    break;
                case (R.id.speedBtn):
                    activityWillOpen = "Speed";
                    break;
                case (R.id.currencyBtn):
                    activityWillOpen = "Currency";
                    break;
                case (R.id.powerBtn):
                    activityWillOpen = "Power";
                    break;
                case (R.id.pressureBtn):
                    activityWillOpen = "Pressure";
                    break;
                default:
                    activityWillOpen = "Unit Converter";
            }
            Gadgets.openActivity(MainActivity.this, ConvertActivity.class, "activity", activityWillOpen);
        }
    }

    @Override
    public void onBackPressed() {
        Dialog alertDialog = new Dialog(MainActivity.this);
        alertDialog.setContentView(R.layout.custom_dialog_layout);

        Button yesBtn = alertDialog.findViewById(R.id.dialog_yes_btn);
        Button noBtn = alertDialog.findViewById(R.id.dialog_no_btn);

        //removing default background
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
        noBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }
}