package com.example.insideseoul.DBResource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public JSONArray getRecentData(int number_of_data, String name, int boardIdx) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM " + tb_name + " WHERE name = '" + name + "' AND board_idx = " + boardIdx, null);
        JSONArray resultSet = new JSONArray();
        cursor.moveToLast();
        int index = 0;
        while (index < number_of_data) {

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

    //새로운 데이터 입력
    public void insert(String name, int boardIdx) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("board_idx", boardIdx);
        db.insert(tb_name, null, values);

        db.close();
    }

    //Primary key인 id를 이용해서 검색 후 삭제
    public void delete(String name, int boardIdx) throws JSONException {

        // 입력한 항목과 일치하는 행 삭제
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + tb_name + " WHERE name= '" + name + "' and board_idx = " + boardIdx + ";");
        db.close();
    }
}
