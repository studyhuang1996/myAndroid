package com.example.huang.a27huangjy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.huang.javabean.Administrate;
import com.example.huang.javabean.Attendance;
import com.example.huang.javabean.Student;
import com.example.huang.javabean.Teacher;
import com.example.huang.javabean.Work;
import com.example.huang.javabean.courseGrade;

import java.util.List;

/**
 * Created by huang on 2017/4/28.
 */

public class DBOperator {

    private  DBHelper dbHelper;
    private static SQLiteDatabase db;
    public DBOperator(Context context)
    {
        dbHelper =new DBHelper(context,"Mydatabase.db" , null,4);
        db=dbHelper.getWritableDatabase();

    }

    //添加学生数据
    public static void add(List<Student> stu)
    {
        for(Student student :stu)
        {
    db.execSQL("insert into student(stu_id,stu_name,stu_pw,stu_sex,stu_classname,stu_photo,stu_major) values(?,?,?,?,?,?,?)",
            new Object[]{student.getId(),student.getName(),student.getpasswd(),student.getSex(),student.getclassName(),student.getstuPhoto(),student.getMajor()});
}
}

    public static  void teaAdd(List<Teacher> tea)
    {
        for( Teacher teacher :tea)
        {
            db.execSQL("insert into teacher(tea_id,tea_name,tea_pw,tea_sex,tea_jiaoclass,tea_photo,tea_major) values(?,?,?,?,?,?,?)",
                    new Object[]{teacher.getId(),teacher.getName(),teacher.getpasswd(),teacher.getSex(),teacher.getclassName(),teacher.getTeaPhoto(),teacher.getTea_major()});
        }
    }
    public void workAdd(List<Work> work)
    {
        for( Work works :work)
        {
            db.execSQL("insert into work(work_id,work_name,stu_id,tea_id,course_name) values(?,?,?,?,?)",
                    new Object[]{works.getId(),works.getWork_name(),works.getStu_id(),works.getTea_id(),works.getCourse_name()});
        }
    }
    public void addAdmin(List<Administrate> admin)
    {
        for (Administrate admins:admin) {
            db.execSQL("insert into admin(admin_id,admin_pw,admin_name) values(?,?,?)",
                    new Object[]{admins.getAdminId(),admins.getAdmin_pw(),admins.getName()});

        }
    }
    public void addCouserGrade(List<courseGrade> grade)
    {
        for (courseGrade grades:grade) {
            db.execSQL("insert into coursegrade(id,stu_id,tea_id,course_score,course_name) values(?,?,?,?,?)",
                    new Object[]{grades.getId(),grades.getStuid(),grades.getTeaid(),grades.getScore(),grades.getCoursename()});

        }
    }

    public void addAttendance(List<Attendance> att)
    {
        for (Attendance attendance:att) {
            db.execSQL("insert into attendance(attendance_id,stu_id,tea_id,attendance_score,course_name,attendance_name) values(?,?,?,?,?,?)",
                    new Object[]{attendance.getId(),attendance.getStuid(),attendance.getTeaid(),attendance.getScore(),attendance.getCoursename(),attendance.getTextname()});

        }
    }
    public SQLiteDatabase getDatabase(Context context)
    {
        //hjyDBHelper dbHelper=new hjyDBHelper(context, "Mydatabase.db", null, 1);

        //sql=dbHelper.getWritableDatabase();
        return db;
    }
    public Cursor sqlQuery(String strsql,String id,String pw)
    {
        SQLiteDatabase sql=dbHelper.getReadableDatabase();
        Cursor cursor=sql.rawQuery(strsql,new String[]{id,pw});


        return cursor;
    }

    public static void dbclose()
    {
        db.close();
    }

}
