package com.example.huang.admin;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/3.
 */

public class selectCourse extends Activity {
    private ListView listView;
    private List<Course> courses=new ArrayList<>();
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_course);
        listView=(ListView)findViewById(R.id.course_list);
        courseQuery();
        seeCourseAdapter courseAdapter=new seeCourseAdapter(courses);
        listView.setAdapter(courseAdapter);
    }

    public void courseQuery()
    {

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from coursetable",null);
        if(cursor.moveToFirst())
        {
            do{
                int  course_id=cursor.getInt(cursor.getColumnIndex("course_id"));
                String course_name=cursor.getString(cursor.getColumnIndex("course_name"));
                String tea_name=cursor.getString(cursor.getColumnIndex("tea_name"));
                String jiehshu=cursor.getString(cursor.getColumnIndex("jieshu"));
                String week=cursor.getString(cursor.getColumnIndex("day"));
                String classname=cursor.getString(cursor.getColumnIndex("course_class"));
                String  c_address=cursor.getString(cursor.getColumnIndex("course_address"));
                // Toast.makeText(MyApplication.getContext(),"xuehao"+stu_name+stuid+major,Toast.LENGTH_SHORT).show();
                Course course=new Course(course_id,course_name,tea_name,jiehshu,week,classname,c_address);
                courses.add(course);
            }while(cursor.moveToNext());

        }
        cursor.close();
        DBOperator.dbclose();
    }


}