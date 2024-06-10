package com.example.hanxu5.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.hanxu5.bean.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksDAO {
    private Context context;
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;

    public BooksDAO(Context context) {
        this.context = context;
    }

    public void open() throws SQLiteException {
        dbHelper = new MyDBHelper(context);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }


    public long addBooks(Book o) {
        ContentValues values = new ContentValues();
        values.put("studentid", o.studentid);
        values.put("studentname", o.studentname);
        values.put("majoy", o.majoy);
        values.put("booknum", o.booknum);

        return db.insert("tb_Books", null, values);
    }

    public int deletBooks(Book o) {
        return db.delete("tb_Books", "studentid=?", new String[]{String.valueOf(o.studentid)});
    }

    public int updateBooks(Book o) {
        ContentValues value = new ContentValues();
        value.put("studentname", o.studentname);
        value.put("majoy", o.majoy);
        value.put("booknum", o.booknum);
        return db.update("tb_Books", value, "studentid=?", new String[]{String.valueOf(o.studentid)});
    }

    public Book getBooks(String studentid) {
        Cursor cursor = db.query("tb_Books", null, "studentid=?", new String[]{studentid}, null, null, null);
       Book o = new Book();
        while (cursor.moveToNext()) {
            o.studentid = cursor.getString(cursor.getColumnIndex("studentid"));
            o.studentname = cursor.getString(cursor.getColumnIndex("studentname"));
            o.majoy = cursor.getString(cursor.getColumnIndex("majoy"));
            o.booknum = cursor.getString(cursor.getColumnIndex("booknum"));

        }
        return o;
    }

    public ArrayList<Map<String, Object>> getAllbooks() {
        ArrayList<Map<String, Object>> listBooks = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Books", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("studentid", cursor.getString(cursor.getColumnIndex("studentid")));
                map.put("studentname", cursor.getString(cursor.getColumnIndex("studentname")));
                map.put("majoy", cursor.getString(cursor.getColumnIndex("majoy")));
                map.put("booknum", cursor.getString(cursor.getColumnIndex("booknum")));

                listBooks.add(map);
            }
            return listBooks;
        }
    }
}