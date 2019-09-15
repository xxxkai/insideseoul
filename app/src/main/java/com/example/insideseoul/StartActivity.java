package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Handler timer = new Handler();

        timer.postDelayed(new Runnable(){
            public void run(){
                ImageView loading = findViewById(R.id.loading_img);
                loading.setImageDrawable(getResources().getDrawable(R.drawable.loading_1));
            }
        }, 400);

        timer.postDelayed(new Runnable(){
            public void run(){
                ImageView loading = findViewById(R.id.loading_img);
                loading.setImageDrawable(getResources().getDrawable(R.drawable.loading_2));
            }
        }, 800);

        timer.postDelayed(new Runnable(){
            public void run(){
                ImageView loading = findViewById(R.id.loading_img);
                loading.setImageDrawable(getResources().getDrawable(R.drawable.loading_3));
            }
        }, 1200);

        timer.postDelayed(new Runnable(){
            public void run(){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }
}
