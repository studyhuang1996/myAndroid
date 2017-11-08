package com.example.huang.teacher;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;

import java.util.List;

/**
 * Created by huang on 2017/5/15.
 */

public class indexAdapter extends BaseAdapter {


  private List<Course>list;
    private TextView t1;
    private TextView t2;
    private TextView t3;

    private DBOperator dbOperator;
    private SQLiteDatabase db;
    Course course;
  public indexAdapter(List<Course> cou)
  {
      this.list=cou;
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
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.tea_index_item, null);
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
