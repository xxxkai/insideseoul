package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Handler timer = new Handler();

        timer.postDelayed(new Runnable(){

            public void run(){

                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
