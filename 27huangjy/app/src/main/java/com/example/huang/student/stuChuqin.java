package com.example.huang.student;

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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Attendance;
import com.example.huang.teacher.chuqin;

import java.util.ArrayList;

/**
 * Created by huang on 2017/5/15.
 */

public class stuChuqin extends Activity {
    private ListView listView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;

    private ArrayList<Attendance> atte = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_chuqin);
        listView = (ListView) findViewById(R.id.list);

        chuqins();
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyApplication.getContext(), "weizhi" + position, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void chuqins() {
        Intent intent = getIntent();
        String tid = intent.getStringExtra("sid").toString().trim();
        Log.d("1", tid);
        Toast.makeText(MyApplication.getContext(), "weizhi" + tid, Toast.LENGTH_SHORT).show();
        dbOperator = new DBOperator(this);
        db = dbOperator.getDatabase(this);
        Cursor cursor = db.rawQuery("select * from attendance where stu_id=?", new String[]{tid});
        if (cursor.moveToFirst()) {
            do {
                int stuid = cursor.getInt(cursor.getColumnIndex("stu_id"));
                int teaid = cursor.getInt(cursor.getColumnIndex("tea_id"));
                int workid = cursor.getInt(cursor.getColumnIndex("attendance_id"));
                String courseName = cursor.getString(cursor.getColumnIndex("course_name"));
                int score = cursor.getInt(cursor.getColumnIndex("attendance_score"));
                String name = cursor.getString(cursor.getColumnIndex("attendance_name"));
                //  Log.d("1",workName);
                //   Log.d("1",courseName);S
                //Toast.makeText(chuqin.this,"js"+stuid+teaid+courseName+score,Toast.LENGTH_SHORT).show();
                Attendance attendance = new Attendance(workid, score, stuid, teaid, courseName, name);
                atte.add(attendance);
            } while (cursor.moveToNext());

        }
        cursor.close();
    }

    class MyAdapter extends BaseAdapter {
        // private  List<courseGrade> list;
        private TextView t1;
        private TextView t2;
        private TextView t3;
        private TextView t4;
        private TextView t5;
        private DBOperator dbOperator;
        private SQLiteDatabase db;

     /*  / public  MyAdapter(List<courseGrade> grades){
            this.list=grades;
        }*/

        @Override
        public Object getItem(int position) {
            return atte.get(position);
        }

        @Override
        public int getCount() {
            return atte.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_chuqin_item, null);
            } else {
                view = convertView;
            }
            final Attendance attendan = atte.get(position);
            t1 = (TextView) view.findViewById(R.id.sid);
            t2 = (TextView) view.findViewById(R.id.tid);
            t3 = (TextView) view.findViewById(R.id.courseid);
            t4 = (TextView) view.findViewById(R.id.chunqinname);
            t5 = (TextView) view.findViewById(R.id.sscore);

            t1.setText(attendan.getStuid() + "");
            t2.setText(attendan.getTeaid() + "");
            t3.setText(attendan.getCoursename());
            t4.setText(attendan.getTextname());
            t5.setText(attendan.getScore() + "");

            return view;
        }
    }
}
