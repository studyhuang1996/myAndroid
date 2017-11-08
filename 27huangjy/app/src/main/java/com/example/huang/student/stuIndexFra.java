package com.example.huang.student;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.courseGrade;
import com.example.huang.teacher.pingjia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by huang on 2017/5/4.
 */

public class stuIndexFra extends Fragment {
    private ListView listView;
    private TextView day;
    private TextView week;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    String weekday;
    String id;
    String classmate;
    final ArrayList<Course> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.stu_index, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        day = (TextView) view.findViewById(R.id.day);
        week = (TextView) view.findViewById(R.id.weekday);

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat weeks = new SimpleDateFormat("EEEE");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        weekday = weeks.format(date);
        String today = format.format(date);
        day.setText(today);
        week.setText(weekday);
     /*   MyAdapter pingjia=new pingjia.MyAdapter(grades);
        listView.setAdapter(pingjia);*/
        coursequery();
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);

        return view;
    }


    public void coursequery() {

        Bundle bundle = getArguments();
        id = bundle.getString("stuid");

        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());
        Cursor cur = db.rawQuery("select * from  student where stu_id=?", new String[]{id});
        if (cur.moveToNext()) {
            classmate = cur.getString(cur.getColumnIndex("stu_classname")).trim();
            Toast.makeText(MyApplication.getContext(), "banji"+classmate, Toast.LENGTH_SHORT).show();
        }

        Cursor cursor = db.rawQuery("select * from  coursetable where course_class=? and day=?", new String[]{classmate,weekday});
        if (cursor.moveToFirst()) {
            do {

                String jie = cursor.getString(cursor.getColumnIndex("jieshu"));
                String add = cursor.getString(cursor.getColumnIndex("course_address"));
                String courseName = cursor.getString(cursor.getColumnIndex("course_name"));

                Course course = new Course(courseName, jie, add);
                list.add(course);
            } while (cursor.moveToNext());

        } else {
            Toast.makeText(MyApplication.getContext(), "今天没课", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

    class MyAdapter extends BaseAdapter {

        private TextView t1;
        private TextView t2;
        private TextView t3;

        private DBOperator dbOperator;
        private SQLiteDatabase db;
        Course course;

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
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_index_item, null);
            } else {
                view = convertView;
            }
            course = list.get(position);
            t1 = (TextView) view.findViewById(R.id.cname);
            t2 = (TextView) view.findViewById(R.id.tid);
            t3 = (TextView) view.findViewById(R.id.address);


            t1.setText(course.getCourse_name());
            t2.setText(course.getJieshu());
            t3.setText(course.getCourse_addresss());


            return view;
        }
    }
}