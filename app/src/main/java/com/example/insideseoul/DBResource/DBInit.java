package com.example.insideseoul.DBResource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.insideseoul.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DBInit extends SQLiteOpenHelper {
    Context context;
    MainActivity mainActivity;
    String tb_name;
    int version;
    SQLiteDatabase.CursorFactory factory;

    DBBoard dbBoard; // 게시판
    DBMember dbMember; // 사용자 계정

    SQLiteDatabase db;

    public DBInit(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        tb_name = name;
        this.version = version;
        this.factory = factory;
        db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();

        if(!isTableExists(name,true)) {
            Log.i("paks >>>>>>>>>>>>>>> doesDatabaseExist: ","DB Empty");
            if(name.equals("tbl_member")) {
                sb.append("CREATE TABLE IF NOT EXISTS " + name + " (");
                sb.append("idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
                sb.append("email VARCHAR(200) NOT NULL, ");
                sb.append("password VARCHAR(200) NOT NULL, ");
                sb.append("approval_yn VARCHAR(1) NOT NULL DEFAULT 'N', ");
                sb.append("name VARCHAR(50) NOT NULL, ");
                sb.append("member_class VARCHAR(10) NOT NULL, ");
                sb.append("create_datetime DATETIME NOT NULL DEFAULT (DATETIME('now','localtime')), ");
                sb.append("update_datetime DATETIME NULL DEFAULT NULL");
                sb.append(") ");
            } else if(name.equals("tbl_like")) {
                sb.append("CREATE TABLE IF NOT EXISTS " + name + " (");
                sb.append("idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
                sb.append("name VARCHAR(50) NOT NULL, ");
                sb.append("board_idx INTEGER NOT NULL");
                sb.append(") ");
            } else if(name.equals("tbl_board")) {
                sb.append("CREATE TABLE IF NOT EXISTS " + name + " (");
                sb.append("idx INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
                sb.append("create_id VARCHAR(50) NOT NULL, ");
                sb.append("update_id VARCHAR(50) DEFAULT NULL, ");
                sb.append("shared_url VARCHAR(1000) DEFAULT NULL, ");
                sb.append("subject VARCHAR(100) NOT NULL, ");
                sb.append("startdate VARCHAR(50) NOT NULL, ");
                sb.append("enddate VARCHAR(50) NOT NULL, ");
                sb.append("intro_content TEXT NOT NULL, ");
                sb.append("file_path1 VARCHAR(256) DEFAULT NULL, ");
                sb.append("file_path2 VARCHAR(256) DEFAULT NULL, ");
                sb.append("file_path3 VARCHAR(256) DEFAULT NULL, ");
                sb.append("create_datetime DATETIME NOT NULL DEFAULT (DATETIME('now','localtime')), ");
                sb.append("update_datetime DATETIME NULL DEFAULT NULL, ");
                sb.append("board_type VARCHAR(4) DEFAULT NULL");
                sb.append("gu_type VARCHAR(4) DEFAULT NULL");
                sb.append(") ");
            }

            if(name.equals("tbl_member") || name.equals("tbl_like") || name.equals("tbl_board")) {
                db.execSQL(sb.toString());
                if(name.equals("tbl_member")) dbMember = new DBMember(context, "tbl_member", null, 1);
                if(name.equals("tbl_board")) dbBoard = new DBBoard(context, "tbl_board", null, 1);
            }
        } else {
            Log.i("paks >>>>>>>>>>>>>>> doesDatabaseExist: ","DB Exist");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean isTableExists(String tableName, boolean openDb) {
        if(openDb) {
            if(db == null || !db.isOpen()) {
                db = getReadableDatabase();
            }

            if(!db.isReadOnly()) {
                db.close();
                db = getReadableDatabase();
            }
        }

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '"+tableName+"'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }


    //데이터 베이스 데이터의 갯수를 출력
    public long getDataSize() {
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, tb_name);
        db.close();
        return count;
    }
}
