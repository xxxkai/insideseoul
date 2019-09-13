package com.example.insideseoul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MypageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

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
                    }
                });
        alert.setNegativeButton("뒤로가기",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MypageActivity.super.onBackPressed();
                    }
                });

        alert.show();
    }
}
