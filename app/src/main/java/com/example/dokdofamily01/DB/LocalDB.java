package com.example.dokdofamily01.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.dokdofamily01.BaseActivity;

import static android.content.ContentValues.TAG;

/**
 * Created by mapl0 on 2017-08-01.
 */

public class LocalDB extends SQLiteOpenHelper {

//    private static DBSI db;

    static SQLiteDatabase db;
    private String[][] result;

    public LocalDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        result = null;

    }

    public LocalDB() {
        super(BaseActivity.context, "appbook.db", null, 1);

        result = null;
    }

    public LocalDB(Context context) {
        super(context, "appbook.db", null, 1);

        result = null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        setDB(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void query(String query) {

        Log.d("query : ", query);

        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(query);

        db.close();

    }

    public String[][] selectQuery(String query) {
        SQLiteDatabase db = getWritableDatabase();

        Log.d("Query", query);

        Cursor cursor;

        try {
            cursor = db.rawQuery(query, null);

            if (cursor.getCount() != 0) {

                this.result = new String[cursor.getCount()][cursor.getColumnCount()];

//            Log.d("getCount", cursor.getCount() + "");
//            Log.d("getColumnCount", cursor.getColumnCount() + "");

                cursor.moveToFirst();

                int i = 0;

                do {

                    for (int j = 0; j < cursor.getColumnCount(); j++) {

                        this.result[i][j] = cursor.getString(j);
                        System.out.println("Select Result........" + cursor.getColumnName(j) + "... : " + cursor.getString(j));

                    }

                    System.out.println("========================================================================================================");

                    i++;
//                System.out.println("moveToNext...." + cursor.moveToNext());
                } while (cursor.moveToNext());

            } else {
                Log.d("SelectNULL", "NULL");
                result = null;
            }

        } catch (SQLiteException e) {
            this.result = null;
        }


        db.close();
        return result;

    }

    public void update(String item, String price) {
        SQLiteDatabase db = getWritableDatabase();
    }

    public void delete(String item) {
        SQLiteDatabase db = getWritableDatabase();
    }

    private void setDB(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS Prologue (isFirst integer(1) default 1);");

    }

    public void dropTable() {

        db.execSQL("DROP TABLE IF EXISTS Prologue;");

    }

    public void checkTable() {

        SQLiteDatabase db = getWritableDatabase();
        Cursor c;
        try {
            c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

            if (c.moveToFirst()) {

                for (; ; ) {

                    Log.e(TAG, "table name : " + c.getString(0));

                    if (!c.moveToNext())

                        break;

                }

            }
        } catch (SQLiteException e) {

            Log.d("SearchTable", "table is nothing");
        }

    }

}
