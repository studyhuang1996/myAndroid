package com.example.huang.teacher;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Work;
import com.example.huang.javabean.courseGrade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/14.
 */

public class pingjia extends Activity {
    private ListView listView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;

    private ArrayList<courseGrade> grades=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tea_pingjia);
        listView=(ListView)findViewById(R.id.list);

        course();
        MyAdapter pingjia=new MyAdapter(grades);
        listView.setAdapter(pingjia);

    }

    public  void  course()
    {
        Intent intent=getIntent();
        String tid=intent.getStringExtra("tid").toString().trim();
        Log.d("1",tid);
        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from coursegrade where tea_id=?",new String[]{tid});
        if(cursor.moveToFirst())
        {
            do{
                int  stuid=cursor.getInt(cursor.getColumnIndex("stu_id"));
                int teaid=cursor.getInt(cursor.getColumnIndex("tea_id"));
                int workid=cursor.getInt(cursor.getColumnIndex("id"));
                String  courseName=cursor.getString(cursor.getColumnIndex("course_name"));
                int score=cursor.getInt(cursor.getColumnIndex("course_score"));
                //  Log.d("1",workName);
                //   Log.d("1",courseName);S
                  Toast.makeText(pingjia.this,"js"+stuid+teaid+courseName+score,Toast.LENGTH_SHORT).show();
               courseGrade work=new courseGrade(workid,score,stuid,teaid,courseName);
                grades.add(work);
            }while(cursor.moveToNext());

        }
        cursor.close();
    }
    class MyAdapter extends BaseAdapter {
        private  List<courseGrade> list;
        private TextView t1;
        private TextView t2;
        private TextView t3;
        private TextView t4;
        private ImageView imageButton;
        private DBOperator dbOperator;
        private SQLiteDatabase db;

         courseGrade grade;
        public  MyAdapter(List<courseGrade> grades){
            this.list=grades;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.pingjia_item, null);
            } else {
                view = convertView;
            }
                grade = list.get(position);
            t1 = (TextView) view.findViewById(R.id.sid);
            t2=(TextView) view.findViewById(R.id.tid);
            t3 = (TextView) view.findViewById(R.id.cname);
            t4 = (TextView) view.findViewById(R.id.sscore);
            imageButton=(ImageView)view.findViewById(R.id.imageButton2);

            t1.setText(grade.getStuid()+"");
            t2.setText(grade.getTeaid()+"");
            t3.setText(grade.getCoursename());
            t4.setText(grade.getScore()+"");

            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LayoutInflater factory = LayoutInflater.from(pingjia.this);//提示框
                    final View view = factory.inflate(R.layout.editext_dialog, null);//这里必须是final的
                    final EditText edit=(EditText)view.findViewById(R.id.editText);//获得输入框对象
                    new AlertDialog.Builder(pingjia.this).setTitle("添加成绩")
                            .setView(view).setPositiveButton("ADD", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String score = edit.getText().toString().trim();
                            Toast.makeText(pingjia.this,"score"+score+grade.getStuid(),Toast.LENGTH_SHORT).show();
                            dbOperator=new DBOperator(getApplicationContext());
                            db=dbOperator.getDatabase(getApplicationContext());
                            db.execSQL("update  coursegrade set course_score=? where stu_id=?", new String[]{score, grade.getStuid()+ ""});
                            notifyDataSetChanged();
                        }
                    }).setNegativeButton("CANCLE", null).create().show();
                } });

            return view;
        }
    }



}

