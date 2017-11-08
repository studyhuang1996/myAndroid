package com.example.huang.a27huangjy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huang.admin.adminIndex;
import com.example.huang.javabean.Administrate;
import com.example.huang.javabean.Attendance;
import com.example.huang.javabean.Work;
import com.example.huang.javabean.courseGrade;

import java.util.ArrayList;

/**
 * Created by huang on 2017/4/28.
 */

public class Admin extends Activity {

    private EditText adminid;
    private EditText adminpw;
    private Button adminlogin;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    private  Button insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);
        adminid=(EditText)findViewById(R.id.user_id);
        adminpw=(EditText)findViewById(R.id.passwd);
        adminlogin=(Button)findViewById(R.id.login_btn);
        insert=(Button) findViewById(R.id.gr);
        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);

    }
    public void insertadmin(View view)
    {

        ArrayList<Attendance> attendances=new ArrayList<>();
        Attendance cg1=new Attendance(1,60,10001,314891,"android","kuangke");
        Attendance cg2=new Attendance(2,60,10002,314891,"android","kuangke");
        Attendance cg3=new Attendance(3,60,10003,314891,"android","kuangke");

        Attendance cg4=new Attendance(4,60,10001,314892,"设计模式 ","出勤情况");
        Attendance cg5=new Attendance(5,60,10002,314892,"设计模式","出勤情况");
        Attendance cg6=new Attendance(6,60,10003,314892,"设计模式","出勤情况");

        attendances.add(cg1);
        attendances.add(cg2);
        attendances.add(cg3);
        attendances.add(cg4);
        attendances.add(cg5);
        attendances.add(cg6);
        dbOperator.addAttendance(attendances);

     /*  ArrayList<Work> works=new ArrayList<>();
        Work work1=new Work(1,10001,"android","实验一",314891,60);
        Work work2=new Work(2,10001,"android","实验二",314891,60);
        Work work3=new Work(3,10001,"android","实验三",314891,60);
        Work work4=new Work(4,10001,"设计模式","实验三",314892,60);
        Work work5=new Work(5,10001,"设计模式","实验一",314892,60);
     //   Work work6=new Work(5,10001,"设计模式","实验二",314892,60);
        works.add(work4);
        works.add(work5);
        works.add(work1);
        works.add(work2);
        works.add(work3);
        dbOperator.workAdd(works);

       ArrayList<Administrate> administrates = new ArrayList<>();
        Administrate adminr = new Administrate(123456, "superHuang", "123123");
        administrates.add(adminr);
        dbOperator.addAdmin(administrates);

        ArrayList<courseGrade> courseGrades=new ArrayList<>();
        courseGrade cg1=new courseGrade(1,60,10001,314891,"android");
        courseGrade cg2=new courseGrade(2,60,10001,314892,"设计模式 ");

        courseGrades.add(cg1);
        courseGrades.add(cg2);

        dbOperator.addCouserGrade(courseGrades);*/

    }
    public void alogin(View view)
    {
        String id=adminid.getText().toString().trim();
        String pw=adminpw.getText().toString().trim();
        Cursor cursor=db.rawQuery("select * from admin where admin_id=? and admin_pw=?", new String[]{id,pw});

        if(cursor.moveToNext())
        {
            String aid=cursor.getString(cursor.getColumnIndex("admin_id"));
            String aname=cursor.getString(cursor.getColumnIndex("admin_name"));
            Intent  intent=new Intent(Admin.this,adminIndex.class);
            intent.putExtra("id",aid);
            intent.putExtra("name",aname);
              startActivity(intent);
        }

    }
}
