package com.example.insideseoul;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static boolean graphic_mode = false;
    Button logo_bt;
    Button home_bt;
    Button info_bt;
    Button mypage_bt;
    Button settings_bt;
    Button title_bt;
    Button cat[];
    FrameLayout contents_frame;
    LinearLayout contents[];

    int[] contents_list = { R.id.graphic_view, R.id.category_view,
                            R.id.help_view, R.id.settings_view,
                            R.id.mypage_view, R.id.dummy_view};
    public enum CONTENTS_INDEX {
        GRAPHIC_VIEW(0), CATEGORY_VIEW(1),
        HELP_VIEW(2), SETTINGS_VIEW(3),
        MYPAGE_VIEW(4), DUMMY_VIEW(5);
        private int value;
        private CONTENTS_INDEX(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };
    CONTENTS_INDEX contents_index;
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
        contents_frame = findViewById(R.id.CONTENTS);
        contents = new LinearLayout[contents_list.length];
        cat = new Button[catid_list.length];

        for(int i = 0; i < contents_list.length; i++) {
            contents[i] = findViewById(contents_list[i]);
        }
        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());

        for(int i = 0; i < catid_list.length; i++) {
            cat[i] = findViewById(catid_list[i]);
            //cat[i].setText(catName[i]);
        }

        showMsg("어플리케이션 사용 준비가\n완료되었습니다.");

    }

    public void goTest(View v){
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
    }

    public void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private void onlyOneVisible(int index){
        for(int i = 0; i < contents.length; i++)
            contents[i].setVisibility(View.GONE);
        contents[index].setVisibility(View.VISIBLE);
    }

    public void changeMode(View v){
        if(graphic_mode) {
            showMsg("그래픽 보기로 이동합니다.");
            onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
        }else {
            showMsg("카테고리 보기로 이동합니다.");
            onlyOneVisible(contents_index.CATEGORY_VIEW.getValue());
        }
        graphic_mode = !graphic_mode;
    }

    public void goHome(View v){
        showMsg("홈 화면으로 이동합니다.");
        graphic_mode = false;
        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
    }
    public void showHelp(View v){
        showMsg("도움말 화면으로 이동합니다.");
        onlyOneVisible(contents_index.HELP_VIEW.getValue());
    }
    public void showMypage(View v){
        Intent intent = new Intent(getApplicationContext(), MypageActivity.class);
        startActivity(intent);

    }
    public void showSettings(View v){
        showMsg("설정 화면으로 이동합니다.");
        onlyOneVisible(contents_index.SETTINGS_VIEW.getValue());
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
