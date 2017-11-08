package com.example.huang.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Student;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/2.
 */

public class selectStu extends Activity {

    private ListView listView;
    private List<Student> students=new ArrayList<>();
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_student);
        listView=(ListView)findViewById(R.id.stu_list);
        stuQuery();
        seeStuAdapter stuAdapter=new seeStuAdapter(students);
        listView.setAdapter(stuAdapter);
    }

    public void stuQuery()
    {

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from student",null);
        if(cursor.moveToFirst())
        {
            do{
                int  stuid=cursor.getInt(cursor.getColumnIndex("stu_id"));
                String stu_name=cursor.getString(cursor.getColumnIndex("stu_name"));
                String pw=cursor.getString(cursor.getColumnIndex("stu_pw"));
                   String major=cursor.getString(cursor.getColumnIndex("stu_major"));
                String stu_photo=cursor.getString(cursor.getColumnIndex("stu_photo"));
                String classname=cursor.getString(cursor.getColumnIndex("stu_classname"));
                String  stu_sex=cursor.getString(cursor.getColumnIndex("stu_sex"));
               // Toast.makeText(MyApplication.getContext(),"xuehao"+stu_name+stuid+major,Toast.LENGTH_SHORT).show();
                Student student=new Student(stuid,stu_name,pw,stu_sex,stu_photo,classname,major);
                students.add(student);
            }while(cursor.moveToNext());

        }
        cursor.close();
        DBOperator.dbclose();
    }
}
