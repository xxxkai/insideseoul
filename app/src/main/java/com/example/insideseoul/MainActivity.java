package com.example.insideseoul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static boolean graphic_mode = false;
    private boolean login_success = false;

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
    int next_view;


    int[] line_ids = { 		R.id.local_line_01, R.id.local_line_02, R.id.local_line_03, R.id.local_line_04,
                            R.id.local_line_05, R.id.local_line_06, R.id.local_line_07, R.id.local_line_08,
                            R.id.local_line_09, R.id.local_line_10, R.id.local_line_11, R.id.local_line_12,
                            R.id.local_line_13
    };
    int[] icon_ids = { 	R.id.local_00, R.id.local_01, R.id.local_02, R.id.local_03, R.id.local_04,
                        R.id.local_05, R.id.local_06, R.id.local_07, R.id.local_08, R.id.local_09,
                        R.id.local_10, R.id.local_11, R.id.local_12, R.id.local_13, R.id.local_14,
                        R.id.local_15, R.id.local_16, R.id.local_17, R.id.local_18, R.id.local_19,
                        R.id.local_20, R.id.local_21, R.id.local_22, R.id.local_23, R.id.local_24
    };
    int[] alert_ids = { 	R.id.local_alert_00, R.id.local_alert_01, R.id.local_alert_02, R.id.local_alert_03, R.id.local_alert_04,
                            R.id.local_alert_05, R.id.local_alert_06, R.id.local_alert_07, R.id.local_alert_08, R.id.local_alert_09,
                            R.id.local_alert_10, R.id.local_alert_11, R.id.local_alert_12, R.id.local_alert_13, R.id.local_alert_14,
                            R.id.local_alert_15, R.id.local_alert_16, R.id.local_alert_17, R.id.local_alert_18, R.id.local_alert_19,
                            R.id.local_alert_20, R.id.local_alert_21, R.id.local_alert_22, R.id.local_alert_23, R.id.local_alert_24
    };
    int[] img_ids = { 	R.id.local_img_00, R.id.local_img_01, R.id.local_img_02, R.id.local_img_03, R.id.local_img_04,
                        R.id.local_img_05, R.id.local_img_06, R.id.local_img_07, R.id.local_img_08, R.id.local_img_09,
                        R.id.local_img_10, R.id.local_img_11, R.id.local_img_12, R.id.local_img_13, R.id.local_img_14,
                        R.id.local_img_15, R.id.local_img_16, R.id.local_img_17, R.id.local_img_18, R.id.local_img_19,
                        R.id.local_img_20, R.id.local_img_21, R.id.local_img_22, R.id.local_img_23, R.id.local_img_24
    };
    int[] img_drawables = { 	R.drawable.map_00, R.drawable.map_01, R.drawable.map_02, R.drawable.map_03, R.drawable.map_04,
                                R.drawable.map_05, R.drawable.map_06, R.drawable.map_07, R.drawable.map_08, R.drawable.map_09,
                                R.drawable.map_10, R.drawable.map_11, R.drawable.map_12, R.drawable.map_13, R.drawable.map_14,
                                R.drawable.map_15, R.drawable.map_16, R.drawable.map_17, R.drawable.map_18, R.drawable.map_19,
                                R.drawable.map_20, R.drawable.map_21, R.drawable.map_22, R.drawable.map_23, R.drawable.map_24
    };
    int[] name_ids = { 	R.id.local_name_00, R.id.local_name_01, R.id.local_name_02, R.id.local_name_03, R.id.local_name_04,
                        R.id.local_name_05, R.id.local_name_06, R.id.local_name_07, R.id.local_name_08, R.id.local_name_09,
                        R.id.local_name_10, R.id.local_name_11, R.id.local_name_12, R.id.local_name_13, R.id.local_name_14,
                        R.id.local_name_15, R.id.local_name_16, R.id.local_name_17, R.id.local_name_18, R.id.local_name_19,
                        R.id.local_name_20, R.id.local_name_21, R.id.local_name_22, R.id.local_name_23, R.id.local_name_24
    };
    int[] contents_list = { R.id.graphic_view, R.id.map_all_view,
                            R.id.settings_view, R.id.mypage_view,
                            R.id.language_view, R.id.signup_view,
                            R.id.web_view, R.id.search_result_view,
                            R.id.result_detail_view, R.id.question_view,
                            R.id.login_view};

    public enum CONTENTS_INDEX {
        GRAPHIC_VIEW(0), MAP_ALL_VIEW(1),
        SETTINGS_VIEW(2), MYPAGE_VIEW(3),
        LANGUAGE_VIEW(4), SIGNUP_VIEW(5),
        WEB_VIEW(6), SEARCH_RESULT_VIEW(7),
        RESULT_DETAIL_VIEW(8), QUESTION_VIEW(9),
        LOGIN_VIEW(10);

        private int value;
        private CONTENTS_INDEX(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    };
    CONTENTS_INDEX contents_index;
    private long backKeyPressedTime = 0;

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


        for(int i = 0; i < contents_list.length; i++) {
            contents[i] = findViewById(contents_list[i]);
        }

        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
        priv_view = contents_index.GRAPHIC_VIEW.getValue();
        showMsg("어플리케이션 사용 준비가\n완료되었습니다.");

    }

    public void onBackPressed(){
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showMsg("한번 더 누르면 종료됩니다.");
            onlyOneVisible(priv_view);
            return;
        } if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            this.finish();
        }
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
        onlyOneVisible(contents_index.SIGNUP_VIEW.getValue());
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
        if(index == contents_index.GRAPHIC_VIEW.getValue() || index == contents_index.MAP_ALL_VIEW.getValue()) {
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
            showMsg("지도 보기로 이동합니다.");
            onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
        }else {
            showMsg("구역별 보기로 이동합니다.");
            initMap('a');
            ((TextView)findViewById(R.id.local_name)).setText("서울시 전체");
            onlyOneVisible(contents_index.MAP_ALL_VIEW.getValue());

        }
        graphic_mode = !graphic_mode;
    }

    private void initMap(char local){
        final LinearLayout[] icons = new LinearLayout[icon_ids.length];
        Button[] alerts = new Button[alert_ids.length];
        ImageView[] map_imgs = new ImageView[img_ids.length];
        final TextView[] names = new TextView[name_ids.length];

        Random rand = new Random();
        int alert_num = 0; // 이벤트 개수
        int congestion = 0; // 혼잡도
        int visible_icon_count = 0; // 표시할 아이콘의 수
        int visible_line_count = 0; // 표시할 아이콘의 수

        // 초기화
        for(int i = 0; i < icon_ids.length; i++) {
            // 필요한 뷰만 표시하기 위해 가린다.
            icons[i] = findViewById(icon_ids[i]);
            icons[i].setVisibility(View.INVISIBLE);

            alerts[i] = findViewById(alert_ids[i]);
            map_imgs[i] = findViewById(img_ids[i]);
            names[i] = findViewById(name_ids[i]);
        }

        // 출력할 아이콘 수 계산
        switch (local){
            case 'n' :
                visible_icon_count = GraphicLayout.getLocalCount('n');
                break;
            case 's' :
                visible_icon_count = GraphicLayout.getLocalCount('s');
                break;
            case 'a' :
                visible_icon_count = GraphicLayout.getLocalCount('n');
                visible_icon_count += GraphicLayout.getLocalCount('s');
                break;
        }

        // 아이콘 레이아웃 표시
        for (int i = 0; i < visible_icon_count; i++)
            icons[i].setVisibility(View.VISIBLE);

        // 사용하지 않는 라인 레이아웃 제거
        visible_line_count = (visible_icon_count%2==0)?visible_icon_count/2:visible_icon_count/2+1;
        for (int i = 0; i < visible_line_count; i++)
            findViewById(line_ids[i]).setVisibility(View.VISIBLE);
        for (int i = visible_line_count; i < line_ids.length; i++)
                findViewById(line_ids[i]).setVisibility(View.GONE);

        // 아이콘 데이터 구성
        int offset = GraphicLayout.getNameStartIndex(local);
        for(int i = 0; i < visible_icon_count; i++){
            // 더미데이터 생성
            alert_num = rand.nextInt()%5;
            congestion = rand.nextInt()%3;
            alert_num = (alert_num<0)?(alert_num*-1):alert_num;
            congestion = (congestion<0)?((congestion*-1)):(congestion);

            alerts[i].setText(""+alert_num);
            names[i].setText(GraphicLayout.getName(i+offset));
            map_imgs[i].setImageDrawable(getResources().getDrawable(img_drawables[i+offset], getApplicationContext().getTheme()));
            map_imgs[i].setColorFilter(Color.parseColor(GraphicLayout.Colors[congestion]));
        }

        // 클릭 이벤트 등록
        View.OnClickListener clickListener = new View.OnClickListener(){
            public void onClick(View v){
                int id = v.getId();
                for(int i = 0; i < icon_ids.length; i++){
                    if(icon_ids[i] == id) {
                        showMsg(names[i].getText() + "검색 결과");
                    }
                }
                onlyOneVisible(contents_index.SEARCH_RESULT_VIEW.getValue());
            }
        };

        for(int i = 0; i < visible_icon_count; i++){
            icons[i].setOnClickListener(clickListener);
        }
    }

    public void viewMap1(View v){
        showMsg("서울시 북부 목록을 표시합니다.");
        initMap('n');
        ((TextView)findViewById(R.id.local_name)).setText("서울시 북부");
        onlyOneVisible(contents_index.MAP_ALL_VIEW.getValue());
        graphic_mode = !graphic_mode;
    }

    public void viewMap2(View v){
        showMsg("서울시 남부 목록을 표시합니다.");
        initMap('s');
        ((TextView)findViewById(R.id.local_name)).setText("서울시 남부");
        onlyOneVisible(contents_index.MAP_ALL_VIEW.getValue());
        graphic_mode = !graphic_mode;
    }
    public void goHome(View v){
        showMsg("홈 화면으로 이동합니다.");
        graphic_mode = false;
        onlyOneVisible(contents_index.GRAPHIC_VIEW.getValue());
    }
    public void goWebView(View v){
        onlyOneVisible(contents_index.WEB_VIEW.getValue());
        switch (v.getId()){
            case R.id.BT_INFO :
                goURL("https://www.google.com");
                break;
            case R.id.BT_CONTRACT :
                goURL("https://www.daum.net");
                break;
            case R.id.BT_DEVELOPER :
                goURL("https://www.gnu.org/");
                break;
            case R.id.BT_FAQ :
                goURL("https://www.yahoo.com");
                break;
            default:
                goURL("https://www.naver.com");
        }
    }

    public void tryLogin(View v){
        // 이메일 및 아이디 체크
        String email = ((TextView)findViewById(R.id.INPUT_LOGIN_EMAIL)).getText().toString();
        String pass = ((TextView)findViewById(R.id.INPUT_LOGIN_PASSWORD)).getText().toString();

        showMsg("이메일 : " + email);
        showMsg("패스워드 : " + pass);

        if(true)
            login_success = true;
        else
            login_success = false;

        if(login_success)
            onlyOneVisible(next_view);
    }

    public void showMypage(View v) {
        if (login_success) {
            onlyOneVisible(contents_index.MYPAGE_VIEW.getValue());
        }else{
            next_view = contents_index.MYPAGE_VIEW.getValue();
            onlyOneVisible(contents_index.LOGIN_VIEW.getValue());
        }
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
                notSupported(null);
                dialogInterface.dismiss();
            }
        }).show();

    }
    public void notSupported(View v){
        showMsg("해당 기능은 현재 버전에서 지원하지 않습니다.");
    }
    public void goQustion(View v){
        onlyOneVisible(contents_index.QUESTION_VIEW.getValue());
    }
}
