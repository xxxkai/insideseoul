package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {
    ImageView dummy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        dummy = findViewById(R.id.map_dummy);
    }

    protected void onResume(){
        super.onResume();
    }

    public void bt1(View v){
        dummy.setColorFilter(Color.parseColor("#CCCCCC"));
    }

    public void bt2(View v){
        dummy.setColorFilter(Color.parseColor("#FFFFFF"));
    }
    public void bt3(View v){
        dummy.setColorFilter(Color.parseColor("#111111"));
    }
    public void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

}
