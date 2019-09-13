package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button home_bt;
    Button info_bt;
    Button mypage_bt;
    Button settings_bt;
    Button title_bt;
    Button cat[];

    int[] catid_list = {   R.id.BT_CAT1, R.id.BT_CAT2, R.id.BT_CAT3, R.id.BT_CAT4,
            R.id.BT_CAT5, R.id.BT_CAT6, R.id.BT_CAT7, R.id.BT_CAT8};
    String[] catName = {  "모든",   "교통",   "안전",   "주택",
                          "경제",   "환경",   "환경",   "복지"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        home_bt = findViewById(R.id.BT_HOME);
        info_bt = findViewById(R.id.BT_INFO);
        mypage_bt = findViewById(R.id.BT_MYPAGE);
        settings_bt = findViewById(R.id.BT_SETTINGS);
        title_bt = findViewById(R.id.BT_TITLE);

        cat = new Button[8];
        for(int i = 0; i < catid_list.length; i++) {
            cat[i] = findViewById(catid_list[i]);
            //cat[i].setText(catName[i]);
        }

        showMsg("어플리케이션 사용 준비가\n완료되었습니다.");
    }

    public void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    public void goHome(View v){
        showMsg("홈 화면으로 이동합니다.");
    }
    public void showInfo(View v){
        showMsg("준비중입니다.");
    }
    public void showMypage(View v){
        Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
        startActivity(intent);

    }
    public void showSettings(View v){
        showMsg("준비중입니다.");
    }
    public void goCategory(View v){
        int index = 0;
        for(;index<catid_list.length; index++)
            if(v.getId() == catid_list[index])
                break;

         showMsg(catName[index] + " 분야로 이동합니다.");
         // 해당 카테고리로 이동
    }

}
