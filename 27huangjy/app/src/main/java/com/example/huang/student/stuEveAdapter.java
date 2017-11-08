package com.example.huang.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.courseGrade;

import java.util.List;

/**
 * Created by huang on 2017/5/14.
 */

public class stuEveAdapter extends BaseAdapter {

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private List<courseGrade> list;

    public stuEveAdapter(List<courseGrade> work) {

        this.list = work;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //  work=WorkgetItem(position);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //super.getView(position, convertView, parent);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_eve_item, null);
        } else {
            view = convertView;
        }
        courseGrade grades = list.get(position);
        t1 = (TextView) view.findViewById(R.id.sid);
        t2 = (TextView) view.findViewById(R.id.tid);
        t3 = (TextView) view.findViewById(R.id.sscore);
        t4=(TextView)view.findViewById(R.id.cname);
        t1.setText(grades.getStuid());
        t2.setText(grades.getTeaid());
        t3.setText(grades.getScore());
        t4.setText(grades.getCoursename());
        return view;
    }

}
