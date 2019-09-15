package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    protected void onResume(){
        super.onResume();


        FrameLayout frame = findViewById(R.id.frame);
        LinearLayout bt1 = findViewById(R.id.test1);
        LinearLayout bt2 = findViewById(R.id.test2);
        bt2.setVisibility(View.GONE);
        //bt2.setVisibility(View.VISIBLE);
        //frame.removeViewAt(1);
        //frame.removeAllViews();
        //frame.addView(findViewById(R.id.test3));
        //TextView dummy = findViewById(R.id.test1);
        //frame.addView(dummy);
        //frame.addView(findViewById(R.id.dummy_layout));
        //frame.addView(findViewById(R.id.dummy_layout), 0);
    }

    public void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
    public void test1(View v){
        showMsg("1번");
    }
    public void test2(View v){
        showMsg("2번");
    }
}
