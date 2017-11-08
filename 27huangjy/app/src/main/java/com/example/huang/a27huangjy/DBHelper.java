package com.example.huang.a27huangjy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huang on 2017/4/28.
 */

public class DBHelper extends SQLiteOpenHelper {

    Context mContext;

    public DBHelper(Context context, String name,SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name,null,version);
        // TODO Auto-generated constructor stub
        mContext=context;

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        // TODO Auto-generated method stub
        //学生表
        db.execSQL("create table student(" +
                "stu_id integer  primary key ," +
                "stu_name varchar(20)," +
                "stu_pw varchar(10)," +
                "stu_sex varchar(10)," +
                "stu_classname varchar(20)," +
                "stu_photo varchar(30)," +
                "stu_major varchar(20))");
        //教师表
        db.execSQL("create table teacher(" +
                "tea_id integer primary key," +
                "tea_name varchar(20)," +
                "tea_pw varchar(10)," +
                "tea_sex varchar(10)," +
                "tea_jiaoclass varchar(20)," +
                "tea_photo varchar(20)," +
                "tea_major varchar(20))");
        //管理员表
        db.execSQL("create table admin(" +
                "admin_id int primary key," +
                "admin_pw varchar(10)," +
                "admin_name varchar(20))");
        //课程表
        db.execSQL("create table coursetable(" +
                "course_id Integer primary key ," +
                "course_name varchar(20)," +
                "tea_name varchar(20)," +
                "stu_classname varchar(20)," +
                "jieshu varchar(10)," +
                "day varchar(20)," +
                "course_address varchar(20))");
        //作业表
        db.execSQL("create table work(" +
                "work_id Integer primary key   ," +
                "work_name varchar(20)," +
                "work_score int ," +
                "course_name varchar(20)," +
                "stu_id  int," +
                "tea_id int)");
        //课程评价表
        db.execSQL("create table coursegrade(" +
               "id Integer primary key autoincrement," +
                "stu_id int," +
                "course_name varchar(20)," +
                "tea_id int," +
                "course_score int)");
        //出勤表
        db.execSQL("create table attendance(" +
                "attendance_id Integer primary key, " +
                "attendance_name varchar(30)," +
                "stu_id int," +
                "course_name varchar(20)," +
                "tea_id int," +
                "attendance_score int)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        //db.execSQL("alter table student ");
        db.execSQL("drop table if exists admin");
        db.execSQL("drop table if exists student");
        db.execSQL("drop table if exists teacher");
        db.execSQL("drop table if exists work");
        db.execSQL("drop table if exists coursetable");
        db.execSQL("drop table if exists coursegrade");
        db.execSQL("drop table if exists attendance");
        onCreate(db);
    }

}
