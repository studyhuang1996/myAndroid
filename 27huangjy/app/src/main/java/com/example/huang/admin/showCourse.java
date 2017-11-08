package com.example.huang.admin;

import android.app.Activity;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/3.
 */

public class showCourse extends Activity {
   private GridView gridView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    private List<Course> courses=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursetable);
       gridView =(GridView) findViewById(R.id.table);
        courseQuery();
       courseAdapter  gridAdapter=new courseAdapter(courses);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
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
