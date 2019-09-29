package com.example.insideseoul.DBResource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.insideseoul.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBLike extends SQLiteOpenHelper {
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

    public DBLike(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        tb_name = name;
        this.version = version;
        this.factory = factory;
        SQLiteDatabase db = getWritableDatabase();
    }

    //최근 데이터로 부터 일정 갯수의 데이터를 json array 로 얻어온다.
    public JSONArray getRecentData(String email) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM " + tb_name + " WHERE email = '" + email + "'", null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToLast();
        int index = 0;
        while (index < cursor.getCount()) {

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

                    }
                }

            }
            resultSet.put(rowObject);
            cursor.moveToPrevious();
            index = index + 1;
        }

        cursor.close();
        return resultSet;
    }

    // 회원가입 확인
    public boolean isLike(String email, int boardIdx) {
        boolean result = false;
        Log.i("email", String.valueOf(email));
        Log.i("boardIdx", String.valueOf(boardIdx));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) AS sum FROM " + tb_name + " WHERE email = ? AND board_idx = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(email), String.valueOf(boardIdx)});
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

    //새로운 데이터 입력
    public void insert(String email, int boardIdx) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("board_idx", boardIdx);
        db.insert(tb_name, null, values);

        db.close();
    }

    //Primary key인 id를 이용해서 검색 후 삭제
    public void delete(String email, int boardIdx) throws JSONException {

        // 입력한 항목과 일치하는 행 삭제
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + tb_name + " WHERE email= '" + email + "' and board_idx = " + boardIdx + ";");
        db.close();
    }

    // 관심지역 추출
    public JSONArray getFavLocation(String email) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM " + tb_name + " WHERE email = '" + email + "'", null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToLast();
        int index = 0;
        while (index < cursor.getCount()) {

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

                    }
                }

            }
            resultSet.put(rowObject);
            cursor.moveToPrevious();
            index = index + 1;
        }

        cursor.close();
        return resultSet;
    }
}
