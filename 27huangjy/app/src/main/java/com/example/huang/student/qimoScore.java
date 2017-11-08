package com.example.huang.student;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/15.
 */

public class qimoScore extends Activity {
   private TextView score1;
    private TextView score2;
    private SQLiteDatabase db;
    private DBOperator dbOprator;
    int chuqin;int ketang;
    int chuqin1;int ketang1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_score);
        score1=(TextView)findViewById(R.id.anscore);
        score2=(TextView)findViewById(R.id.shejiscore);
        dbOprator = new DBOperator(MyApplication.getContext());
        db = dbOprator.getDatabase(MyApplication.getContext());
        Intent intent = getIntent();
        String tid = intent.getStringExtra("sid").toString().trim();

        Cursor cursor = db.rawQuery("select * from attendance where stu_id=? and course_name=?", new String[]{tid,"android"});
        if (cursor.moveToNext()) {
            chuqin = cursor.getInt(cursor.getColumnIndex("attendance_score"));
        }
        Cursor cursor1 = db.rawQuery("select * from coursegrade where stu_id=? and course_name=?", new String[]{tid,"android"});
        if (cursor1.moveToNext()) {
            ketang= cursor1.getInt(cursor1.getColumnIndex("course_score"));
        }
        double sco=(chuqin*0.3)+(ketang*0.7);
        score1.setText(sco+"");
        Cursor cursor2 = db.rawQuery("select * from attendance where stu_id=? and course_name=?", new String[]{tid,"设计模式"});
        if (cursor2.moveToNext()) {
            chuqin1 = cursor2.getInt(cursor2.getColumnIndex("attendance_score"));
        }
        Cursor cursor3 = db.rawQuery("select * from coursegrade where stu_id=? and course_name=?", new String[]{tid,"设计模式"});
        if (cursor3.moveToNext()) {
            ketang1= cursor3.getInt(cursor3.getColumnIndex("course_score"));
        }
        double sco2=(chuqin1*0.3)+(ketang1 *0.7);

        score2.setText(sco2+"");
    }
}
