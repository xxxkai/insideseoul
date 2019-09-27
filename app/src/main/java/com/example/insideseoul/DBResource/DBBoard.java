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

public class DBBoard extends SQLiteOpenHelper {
    Context context;
    MainActivity mainActivity;
    String tb_name;
    int version;
    SQLiteDatabase.CursorFactory factory;
    String[] seoulGuArr = {"gangrogu", "gunggu", "yangsangu", "seongdonggu", "gangjin", "dongdaemongu", "gunrangu", "seungbukgu", "gangbukgu", "dobonggu", "nowongu", "enpangu", "seudaemongu", "mapogu", "yangchan", "gangseo", "gurogu", "ganchangu", "yangdongpogu", "dongjacgu", "ganacgu", "seuchogu", "gangnamgu", "seungpagu", "gangdongu"};

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public DBBoard(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, int init) {
        super(context, name, factory, version);
        this.context = context;
        tb_name = name;
        this.version = version;
        this.factory = factory;

        SQLiteDatabase db = getWritableDatabase();
        if(init == 1) {
            initInsert(db);
        }
    }

    // '구'의 값으로 불러오기
    public JSONObject getData(String guType) {
        Log.i("guType", String.valueOf(guType));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + tb_name + " WHERE gu_type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(guType)});
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
        return rowObject;
    }

    // '구'의 값으로 불러오기
    public int getDataCnt(String guType) {
        Log.i("guType", String.valueOf(guType));
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT COUNT(*) AS sum FROM " + tb_name + " WHERE gu_type = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(guType)});
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

        int result = 0;
        try {
            System.out.println(rowObject);
            String tmp_sum = (String)rowObject.get("sum");
            if(tmp_sum != "") result = Integer.parseInt((String)rowObject.get("sum"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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

    // 샘플 데이터
    public void initInsert(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU00\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU00\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU01\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU01\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU02\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU02\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU03\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU03\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU04\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU04\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU05\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU05\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU06\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU06\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU07\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU07\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU08\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU08\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU09\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU09\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU10\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU01\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU01\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU10\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU11\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU11\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU12\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU12\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU13\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU13\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU14\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU14\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU15\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU15\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU16\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU16\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU17\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU17\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU18\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU18\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU19\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU19\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU20\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU20\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU21\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU21\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU22\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU22\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU23\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU23\"}, ");

        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터1111111111111\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU24\"}, ");
        sb.append("{\"create_id\":\"admin1\",");
        sb.append("\"subject\":\"샘플 데이터1\",");
        sb.append("\"startdate\":\"2019.09.01\",");
        sb.append("\"enddate\":\"2019.09.30\",");
        sb.append("\"intro_content\":\"샘플 메인 데이터22222222222222\",");
        sb.append("\"file_path1\":\"R.drawable.map_00\",");
        sb.append("\"board_type\":\"B001\",");
        sb.append("\"gu_type\":\"GU24\"} ");

        sb.append("]");
        try {
            JSONArray jsonArray = new JSONArray(sb.toString());

            for(int i = 0; i < jsonArray.length(); i ++) {
                String create_id = (String)((JSONObject) jsonArray.get(i)).get("create_id");
                String subject = (String)((JSONObject) jsonArray.get(i)).get("subject");
                String startdate = (String)((JSONObject) jsonArray.get(i)).get("startdate");
                String enddate = (String)((JSONObject) jsonArray.get(i)).get("enddate");
                String intro_content = (String)((JSONObject) jsonArray.get(i)).get("intro_content");
                String file_path1 = (String)((JSONObject) jsonArray.get(i)).get("file_path1");
                String board_type = (String)((JSONObject) jsonArray.get(i)).get("board_type");
                String gu_type = (String)((JSONObject) jsonArray.get(i)).get("gu_type");

                ContentValues values = new ContentValues();
                values.put("create_id", create_id);
                values.put("subject", subject);
                values.put("startdate", startdate);
                values.put("enddate", enddate);
                values.put("intro_content", intro_content);
                values.put("file_path1", file_path1);
                values.put("board_type", board_type);
                values.put("gu_type", gu_type);
                db.insert(tb_name, null, values);
                System.out.println("paks >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SUCCESS");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("paks >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ERROR");
        }
    }
}
