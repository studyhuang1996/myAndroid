package com.example.huang.teacher;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by huang on 2017/5/2.
 */

public class CourseTable extends Activity {
    private teaCourseTable gVadapter;
    private GridView detailCource;
    private String[][] contents;
    private List<String> dataList;
    private DBOperator dbOperator;
    SQLiteDatabase db;
    String teaid;
    String name;
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_table);

        Intent intent=getIntent();
        teaid=intent.getStringExtra("tid").trim();

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from teacher where tea_id=? ", new String[]{teaid});

          if (cursor.moveToNext()) {
              name=cursor.getString(cursor.getColumnIndex("tea_name")).trim();
          }
        detailCource = (GridView)findViewById(R.id.courceDetail);

              fillStringArray();
                gVadapter = new teaCourseTable(this);
                gVadapter.setContent(contents, 4, 7);
                detailCource.setAdapter(gVadapter);


            }

            public void fillStringArray() {

                Cursor cursor=db.rawQuery("select * from coursetable where tea_name=?",new String[]{name});
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
                            else if(day.equals("星期二")&& (jie-1)==i )
                            {
                                contents[i][1] = courseName + "\n" + address+"\n"+banji;
                            }
                            else if(day.equals("星期三")&& (jie-1)==i)
                            {
                                contents[i][2] = courseName + "\n" + address+"\n"+banji;
                            }
                            else if(day.equals("星期四")&& (jie-1)==i)
                            {
                                contents[i][3] = courseName + "\n" + address+"\n"+banji;
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




