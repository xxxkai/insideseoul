package com.example.insideseoul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.insideseoul.DBResource.DBBoard;
import com.example.insideseoul.DBResource.DBInit;
import com.example.insideseoul.DBResource.DBMember;
import com.example.insideseoul.OpenAPI.GuJSONParser;
import com.example.insideseoul.OpenAPI.MetroJSONParser;

import java.util.ArrayList;
import java.util.List;
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


    /* 19.09.23, 회원가입 // */
    private EditText username;
    private EditText email;
    private EditText passwd;
    private EditText chkpasswd;
    private CheckBox agree;
    private Button btnjoin;
    private Button chkemail;

    private String agreeYN = "N";
    private String passwdYN = "N";
    private String chkemailYN =  "N";
    DBInit dbInitMember, dbInitBoard;
    DBBoard dbBoard;
    DBMember dbMember;
    Cipher cipher;
    /* // 19.09.23, 회원가입 */

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

    // 게시글 숫자
    int[] board_cnt = new int[25];

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
    private int[] congestionLevel;

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
        congestionLevel = getCongestionLevel();

        showMsg("어플리케이션 사용 준비가\n완료되었습니다.");

        /* 19.09.23, 회원가입 // */
        cipher = new Cipher();
        dbInitMember = new DBInit(this, "tbl_member", null, 1);
        dbMember = new DBMember(this, "tbl_member", null, 1);

        /* 19.09.25, DB 설정 */
        dbInitBoard = new DBInit(this, "tbl_board", null, 1);
        dbBoard = new DBBoard(this, "tbl_board", null, 1, 2);
        /* 19.09.25, DB 설정 */

        // editText 설정
        username = (EditText) findViewById(R.id.INPUT_USERNAME);
        email = (EditText) findViewById(R.id.INPUT_EMAIL);
        passwd = (EditText) findViewById(R.id.INPUT_PASSWORD);
        chkpasswd = (EditText) findViewById(R.id.INPUT_REPASSWORD);
        agree = (CheckBox) findViewById(R.id.INPUT_AGREE);
        btnjoin = (Button) findViewById(R.id.JOIN_DONE);
        chkemail = (Button) findViewById(R.id.CHECK_EMAIL);

        // 동의 여부 체크
        agree.setOnClickListener(new CheckBox.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(agree.isChecked()) {
                    agreeYN = "Y";
                    showMsg("약관에 동의 하셨습니다.");
                } else {
                    agreeYN = "N";
                    showMsg("약관에 동의하지 않으셨습니다.");
                }
            }
        });

        // 이메일 체크
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 값 변경시 다시 이메일 중복 확인 필요
                chkemailYN = "N";
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 비밀번호 확인 체크
        chkpasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = passwd.getText().toString();
                String chkpassword = chkpasswd.getText().toString();

                if(password.equals(chkpassword)) {
                    passwdYN = "Y";
                } else {
                    passwdYN = "N";
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // 이메일 중복 체크
        chkemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt = email.getText().toString();
                if(emailTxt.length() == 0) {
                    chkemailYN = "N";
                    showMsg("이메일을 먼저 입력해주세요.");
                } else {
                    System.out.println("paks >>>>>>>>>>>>>>>> member.validate(emailTxt): " + dbMember.validate(emailTxt));
                    if(!dbMember.validate(emailTxt)) {
                        chkemailYN = "N";
                        showMsg("이메일을 형식을 확인해주세요.");
                        email.requestFocus();
                        return;
                    } else {
                        // 이메일 중복 체크
                        if(dbMember.hasEmail(emailTxt).equals("200")) {
                            chkemailYN = "Y";
                            showMsg("사용할 수 있는 이메일 입니다.");
                        } else {
                            chkemailYN = "N";
                            showMsg("중복되는 이메일 입니다.");
                        }
                    }
                }
            }
        });
        // 회원가입 버튼 클릭시
        btnjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이름 입력 확인
                if(username.getText().toString().length() == 0) {
                    showMsg("이름을 입력해주세요.");
                    username.requestFocus();
                    return;
                }
                // 이메일 입력 확인
                if(email.getText().toString().length() == 0) {
                    showMsg("이메일을 입력해주세요.");
                    email.requestFocus();
                    return;
                }
                // 비밀번호 입력 확인
                if(passwd.getText().toString().length() == 0) {
                    showMsg("비밀번호를 입력해주세요.");
                    passwd.requestFocus();
                    return;
                }
                // 비밀번호 확인 입력 확인
                if(chkpasswd.getText().toString().length() == 0) {
                    showMsg("비밀번호 확인을 입력해주세요.");
                    chkpasswd.requestFocus();
                    return;
                }
                // 약관동의 확인
                if(agreeYN.equals("N")) {
                    showMsg("약관에 동의해주세요.");
                    return;
                }
                // 이메일 중복 확인
                if(chkemailYN.equals("N")) {
                    showMsg("이메일 중복 확인을 해주세요.");
                    return;
                }
                // 비밀번호 확인
                if(passwdYN.equals("N")) {
                    showMsg("비밀번호가 일치하지 않습니다.");
                    return;
                }

                String emailTxt = email.getText().toString();
                String passwordTxt = Cipher.getBase64(Cipher.doCipher(passwd.getText().toString().getBytes()));
                String nameTxt = username.getText().toString();

                // 이메일 중복 체크
                if(dbMember.hasEmail(emailTxt).equals("500")) {
                    chkemailYN = "N";
                    showMsg("이미 등록된 이메일 입니다.");
                    return;
                }

                dbMember.insert(emailTxt, passwordTxt, "Y", nameTxt, "USR");

                System.out.println();

                showMsg("회원가입이 완료되었습니다.");
            }
        });
        /* // 19.09.23, 회원가입 */

        // 구 마다 리스트 불러오기
        for(int i = 0; i < 25; i ++) {
            String tmp = "";

            if(i < 10) {
                tmp = "GU0" + i;
            }
            else {
                tmp = "GU" + i;
            }
            board_cnt[i] = dbBoard.getDataCnt(tmp);
        }
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

    public void goSignup(View v){
        onlyOneVisible(contents_index.SIGNUP_VIEW.getValue());
    }

    private int[] rankData(int[] data){
        int[] ret = new int[data.length];
        int cnt;

        for (int i = 0; i < data.length; i++) {
            cnt = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[i] < data[j]) {
                    cnt++;
                }
            }
            ret[i] = cnt + 1;
        }
        return ret;
    }

    public void goTest(View v){
/*
        Intent intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
*/
        //onlyOneVisible(contents_index.SIGNUP_VIEW.getValue());
        GuJSONParser guJSONParser = new GuJSONParser();
        MetroJSONParser metroJSONParser = new MetroJSONParser();

        List guList = new ArrayList<Integer>();
        List metroList = new ArrayList<Integer>();
        try {
            guList = guJSONParser.execute().get();
            metroList = metroJSONParser.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* // 실제 파싱된 데이터 불러오기 */


        /* 값 확인용 // */
        showMsg("paks >>>>>>>>>>> guList" + guList);
        showMsg("paks >>>>>>>>>>> metroList" + metroList);
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

    private int[] getCongestionLevel(){
        GuJSONParser guJSONParser = new GuJSONParser();
        MetroJSONParser metroJSONParser = new MetroJSONParser();

        List guList = new ArrayList<Integer>();
        List metroList = new ArrayList<Integer>();
        try {
            guList = guJSONParser.execute().get();
            metroList = metroJSONParser.execute().get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        int[] con = new int[GraphicLayout.getLocalCount('s')+GraphicLayout.getLocalCount('n')];
        int[] rank;

        /* // 실제 파싱된 데이터 불러오기 */
        for(int i = 0; i < con.length; i++){
            con[i] = (Integer.parseInt(guList.get(i).toString())) + (Integer.parseInt(metroList.get(i).toString())*10); // 거주인구 1배수, 유동인구 10배수
        }

        // 순위 계산
        rank = rankData(con);

        // 3단계 혼잡도로 변경
        for(int i = 0; i < rank.length; i++) {
            if(rank[i] < 9) rank[i] = 2;
            else if(rank[i] < 17) rank[i] = 1;
            else rank[i] = 0;
        }

        // 출력(디버그)
        /*
        for(int i = 0; i < rank.length; i++) {
            System.out.println("con["+i+"] = "+con[i] + " rank : " + rank[i]);
        }
        */
        return rank;
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
            alert_num = board_cnt[i];
            congestion = rand.nextInt()%3;
            alert_num = (alert_num<0)?(alert_num*-1):alert_num;


            alerts[i].setText(""+alert_num);
            names[i].setText(GraphicLayout.getName(i+offset));
            map_imgs[i].setImageDrawable(getResources().getDrawable(img_drawables[i+offset], getApplicationContext().getTheme()));

            // 혼잡도 출력
            congestion = congestionLevel[GraphicLayout.getIndex(names[i].getText().toString())];
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
        String encryptPass = Cipher.getBase64(Cipher.doCipher(pass.getBytes()));

        if(email.equals("") || email == null) {
            showMsg("이메일을 입력해주세요");
            return ;
        }
        if(pass.equals("") || pass == null) {
            showMsg("패스워드를 입력해주세요");
            return ;
        }

        // 로그인 확인 됬을 때 처리. (redoma)
        boolean isCorrect = false;
        if(dbMember.isMember(email, encryptPass)) isCorrect = true;

        if(isCorrect) {
            onlyOneVisible(contents_index.SIGNUP_VIEW.getValue());
            // mypage 보일 값 설정
            ((TextView)findViewById(R.id.user_name)).setText("");

        }
        else {
            showMsg("이메일과 비밀번호를 확인해주세요.");
            return ;
        }

        if(isCorrect) login_success = true;
        else login_success = false;

        if(login_success) onlyOneVisible(next_view);
    }

    public void logout(View v) {
        // 회원가입 폼 초기화
        ((TextView)findViewById(R.id.INPUT_LOGIN_EMAIL)).setText("");
        ((TextView)findViewById(R.id.INPUT_LOGIN_PASSWORD)).setText("");
        onlyOneVisible(contents_index.MYPAGE_VIEW.getValue());
        login_success = false;
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
