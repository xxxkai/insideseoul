package com.example.insideseoul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static boolean graphic_mode = false;
    Button change_bt;
    Button home_bt;
    Button info_bt;
    Button mypage_bt;
    Button settings_bt;
    Button title_bt;
    Button cat[];
    WebView webview;
    FrameLayout contents_frame;
    LinearLayout contents[];
    int priv_view;

    int[] contents_list = { R.id.graphic_view, R.id.category_view,
                            R.id.graphic_view2, R.id.graphic_view3,
                            R.id.settings_view, R.id.mypage_view,
                            R.id.language_view, R.id.signup_view,
                            R.id.web_view};
    public enum CONTENTS_INDEX {
        GRAPHIC_VIEW(0), CATEGORY_VIEW(1),
        GRAPHIC_VIEW2(2), GRAPHIC_VIEW3(3),
        SETTINGS_VIEW(4), MYPAGE_VIEW(5),
        LANGUAGE_VIEW(6), SIGNUP_VIEW(7),
        WEB_VIEW(8);
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


        change_bt = findViewById(R.id.BT_CHANGE);
        home_bt = findViewById(R.id.BT_HOME);
        info_bt = findViewById(R.id.BT_INFO);
        mypage_bt = findViewById(R.id.BT_MYPAGE);
        settings_bt = findViewById(R.id.BT_SETTINGS);
        title_bt = findViewById(R.id.BT_TITLE);
        contents_frame = findViewById(R.id.CONTENTS);
        contents = new LinearLayout[contents_list.length];
        webview = findViewById(R.id.web);
        cat = new Button[catid_list.length];


        for(int i = 0; i < contents_list.length; i++) {
            contents[i] = findViewById(contents_list[i]);
        }
        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());

        for(int i = 0; i < catid_list.length; i++) {
            cat[i] = findViewById(catid_list[i]);
        }


        showMsg("어플리케이션 사용 준비가\n완료되었습니다.");

    }

    public void onBackPressed(){
        onlyOneVisible(priv_view);
    }

    private void goURL(String url){
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(url);
    }
    public void goTest(View v){
/*
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
*/
        //onlyOneVisible(contents_index.SIGNUP_VIEW.getValue());
    }

    public void showMsg(String str){
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    private void onlyOneVisible(int index){
        for(int i = 0; i < contents.length; i++) {
            if(contents[i].getVisibility() == View.VISIBLE)
                priv_view = i; // 이전 뷰를 기억한다.
            contents[i].setVisibility(View.GONE);
        }
        // 뒤로 가기 및 보기 모드 처리
        if(index == contents_index.GRAPHIC_VIEW.getValue() || index == contents_index.CATEGORY_VIEW.getValue()) {
            change_bt.setBackground(ContextCompat.getDrawable(this, R.drawable.header_change));
            change_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    changeMode(null);
                }
            });
        }else {
            change_bt.setBackground(ContextCompat.getDrawable(this, R.drawable.header_back));
            change_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { onlyOneVisible(priv_view);
                }
            });
        }
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

    public void viewMap1(View v){
        showMsg("서울시 북부 목록을 표시합니다.");
        onlyOneVisible(contents_index.GRAPHIC_VIEW2.getValue());
        ImageView img[] = new ImageView[3];
        img[0] = findViewById(R.id.map_dummy1);
        img[1] = findViewById(R.id.map_dummy2);


    }

    public void viewMap2(View v){
        showMsg("서울시 남부 목록을 표시합니다.");

    }
    public void goHome(View v){
        showMsg("홈 화면으로 이동합니다.");
        graphic_mode = false;
        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
    }
    public void showHelp(View v){
        showMsg("도움말 화면으로 이동합니다.");
        onlyOneVisible(contents_index.WEB_VIEW.getValue());
        goURL("https://www.naver.com");
    }
    public void showMypage(View v){

        final EditText pw_input = new EditText(this);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(pw_input);
        alert.setTitle("비밀번호 입력");
        alert.setMessage("비밀번호를 입력해주십시오");

        alert.setPositiveButton("입력",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(getApplicationContext(),pw_input.getText().toString() ,Toast.LENGTH_LONG).show();
                        // hash_pw = H(pw+slat)
                        // Compare(hash_pw, stored_hash_pw)
                        // Processing
                        onlyOneVisible(contents_index.MYPAGE_VIEW.getValue());
                    }
                });
        alert.setNegativeButton("뒤로가기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //MainActivity.super.onBackPressed();
                    }
                });

        alert.show();

    }
    public void showSettings(View v){
        showMsg("설정 화면으로 이동합니다.");
        onlyOneVisible(contents_index.SETTINGS_VIEW.getValue());
    }

    public void showContract(View v){
        showMsg("약관 화면으로 이동합니다.");
        onlyOneVisible(contents_index.WEB_VIEW.getValue());
        goURL("https://www.google.com");
    }

    public void goLanguageSetting(View v){
        showMsg("언어 설정 화면으로 이동합니다.");
        onlyOneVisible(contents_index.LANGUAGE_VIEW.getValue());
    }
    public void goCategory(View v){
        int index = 0;
        for(;index<catid_list.length; index++)
            if(v.getId() == catid_list[index])
                break;

         showMsg(catName[index] + " 분야로 이동합니다.");
         // 해당 카테고리로 이동
    }
    public void changeSystemLanguage(View v){

        final CharSequence[] lang = {"한국어", "영어", "일본어"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("사용할 언어를 선택해주세요.");
        builder.setSingleChoiceItems(lang, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showMsg(lang[i].toString()+"를 선택하셨습니다.");
            }
        }).setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

}
