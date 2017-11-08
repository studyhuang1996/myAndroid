package com.example.huang.admin;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Student;
import com.example.huang.javabean.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/2.
 */

public class selectTea extends Activity {

    private ListView listView;
    private List<Teacher> teachers=new ArrayList<>();
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_teacher);
        listView=(ListView)findViewById(R.id.tea_list);
        teaQuery();
        seeTeaAdapter teaAdapter=new seeTeaAdapter(teachers);
        listView.setAdapter(teaAdapter);
    }

    public void teaQuery()
    {

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from teacher",null);
        if(cursor.moveToFirst())
        {
            do{
                int  stuid=cursor.getInt(cursor.getColumnIndex("tea_id"));
                String stu_name=cursor.getString(cursor.getColumnIndex("tea_name"));
                String pw=cursor.getString(cursor.getColumnIndex("tea_pw"));
                String major=cursor.getString(cursor.getColumnIndex("tea_major"));
                String stu_photo=cursor.getString(cursor.getColumnIndex("tea_photo"));
                String classname=cursor.getString(cursor.getColumnIndex("tea_jiaoclass"));
                String  stu_sex=cursor.getString(cursor.getColumnIndex("tea_sex"));
               // Toast.makeText(MyApplication.getContext(),"xuehao"+stu_name+stuid+major,Toast.LENGTH_SHORT).show();
               Teacher teacher=new Teacher(stuid,stu_name,pw,stu_sex,stu_photo,classname,major);
                teachers.add(teacher);
            }while(cursor.moveToNext());

        }
        cursor.close();
        DBOperator.dbclose();
    }
}
