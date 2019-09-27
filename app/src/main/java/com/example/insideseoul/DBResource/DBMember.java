package com.example.insideseoul.DBResource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.insideseoul.MainActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBMember extends SQLiteOpenHelper {

    Context context;
    MainActivity mainActivity;
    String tb_name;
    int version;
    SQLiteDatabase.CursorFactory factory;

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DBMember(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        tb_name = name;
        this.version = version;
        this.factory = factory;
        SQLiteDatabase db = getWritableDatabase();
    }

    //새로운 데이터 입력
    public void insert(String email, String password, String approval_yn, String name, String member_class) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("approval_yn", approval_yn);
        values.put("name", name);
        values.put("member_class", member_class);
        db.insert(tb_name, null, values);

        db.close();
    }

    public static boolean validate(String emailStr) {
        Pattern validateEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = validateEmail.matcher(emailStr);
        return matcher.find();
    }

    // 19.09.23, 특정 id를 갖는 데이터를 json으로 출력
    public String hasEmail(String email) {
        String result = "200";
        Log.i("email", String.valueOf(email));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT email AS sum FROM " + tb_name + " WHERE email = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();
        System.out.println("paks >>>>>>>>>>>>>>>>>>>rowObject: " + rowObject);
        try {
            String tmp = (String)rowObject.get("sum");
            if(tmp.equals(email)) {
                // 중복
                result = "500";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 회원가입 확인
    public boolean isMember(String email, String password) {
        boolean result = false;
        Log.i("email", String.valueOf(email));
        Log.i("password", String.valueOf(password));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) AS sum FROM " + tb_name + " WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email), String.valueOf(password)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();
        System.out.println("paks >>>>>>>>>>>>>>>>>>>rowObject: " + rowObject);
        try {
            String tmp = (String)rowObject.get("sum");

            if(tmp.equals("0")) result = false;
            else result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // 회원가입 정보 호출
    public JSONObject getData(String email, String password) {
        boolean result = false;
        Log.i("email", String.valueOf(email));
        Log.i("password", String.valueOf(password));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tb_name + " WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email), String.valueOf(password)});
        cursor.moveToPosition(0);

        int totalColumn = cursor.getColumnCount();
        JSONObject rowObject = new JSONObject();

        for (int i = 0; i < totalColumn; i++) {
            if (cursor.getColumnName(i) != null) {
                try {
                    if (cursor.getString(i) != null) {
                        rowObject.put(cursor.getColumnName(i), cursor.getString(i));
                    } else {
                        rowObject.put(cursor.getColumnName(i), "");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        cursor.close();
        db.close();

        System.out.println("paks >>>>>>>>>>>>>>>>>>>rowObject: " + rowObject);
        return rowObject;
    }
}