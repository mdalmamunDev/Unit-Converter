package com.example.unitconverterBy_Al_Mamun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    Gadgets gadgets = new Gadgets();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    gadgets.openActivity(SplashActivity.this, MainActivity.class);
                }
            }
        }; thread.start();
    }
}