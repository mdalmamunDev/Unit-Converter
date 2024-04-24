package com.example.unitconverterBy_Al_Mamun;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class Information extends AppCompatActivity {


    RelativeLayout backBtn;
    Button webBtn;
    String fromActivity, currActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        Intent intent = getIntent();
        fromActivity = intent.getStringExtra("info_from");
        currActivity = intent.getStringExtra("activity");

        backBtn = findViewById(R.id.backInfo);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webBtn = findViewById(R.id.button1);
        webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Gadgets.openActivity(Information.this, Gadgets.MY_WEB_LINK);
            }
        });
    }
}