package com.example.huang.student;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.teacher.teaCourseTable;

import java.util.List;

/**
 * Created by huang on 2017/5/4.
 */

public class courseFra extends Fragment {

    private teaCourseTable gVadapter;
    private GridView detailCource;
    private String[][] contents;
    private List<String> dataList;
    private DBOperator dbOperator;
    SQLiteDatabase db;
    String teaid;
    String name;
    String day;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.course_table,container,false);

        Bundle bundle=getArguments();
        id=bundle.getString("stuid");
        dbOperator=new DBOperator(MyApplication.getContext());
        db=dbOperator.getDatabase(MyApplication.getContext());
        Cursor cursor=db.rawQuery("select * from student where stu_id=? ", new String[]{id});

        if (cursor.moveToNext()) {
            name=cursor.getString(cursor.getColumnIndex("stu_classname")).trim();
        }
        detailCource = (GridView)view.findViewById(R.id.courceDetail);

        fillStringArray();
        gVadapter = new teaCourseTable(MyApplication.getContext());
        gVadapter.setContent(contents, 4, 7);
        detailCource.setAdapter(gVadapter);


        return view;

    }
    public void fillStringArray() {

        Cursor cursor=db.rawQuery("select * from coursetable where course_class=?",new String[]{name});
        contents = new String[4][7];
        while(cursor.moveToNext()) {
            day=cursor.getString(cursor.getColumnIndex("day")).trim();
            String jieshu=cursor.getString(cursor.getColumnIndex("jieshu")).trim();
            String courseName=cursor.getString(cursor.getColumnIndex("course_name")).trim();
            String address=cursor.getString(cursor.getColumnIndex("course_address")).trim();
            String banji=cursor.getString(cursor.getColumnIndex("course_class")).trim();
            Toast.makeText(MyApplication.getContext(),"fs"+name+day+jieshu,Toast.LENGTH_SHORT).show();
            int jie=Integer.parseInt(jieshu);

            for (int i = 0; i <4; i++) {
                for (int j = 0; j <7; j++) {

                    if(day.equals("星期一")) {
                        contents[jie-1][0] = courseName + "\n" + address+"\n"+banji;
                    }
                    else if(day.equals("星期二"))
                    {
                        contents[jie-1][1] = courseName + "\n" + address+"\n"+banji;
                    }
                    else if(day.equals("星期三"))
                    {
                        contents[jie-1][2] = courseName + "\n" + address+"\n"+banji;
                    }
                    else if(day.equals("星期四"))
                    {
                        contents[jie-1][3] = courseName + "\n" + address+"\n"+banji;
                    }
                    else if(day.equals("星期五")&& (jie-1)==i)
                    {
                        contents[i][4] = courseName + "\n" + address+"\n"+banji;
                    }
                    else if(day.equals("星期六")&& (jie-1)==i)
                    {
                        contents[i][5] = courseName + "\n" + address+"\n"+banji;
                    } else if(day.equals("星期日")&& (jie-1)==i)
                    {
                        contents[i][6] = courseName + "\n" + address+"\n"+banji;
                    }

                    else
                    {
                        contents[i][j]="";
                    }
                }
            }
        }



    }

}

