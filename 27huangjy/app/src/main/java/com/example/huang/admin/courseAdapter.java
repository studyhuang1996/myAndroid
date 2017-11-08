package com.example.huang.admin;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;

import java.util.List;

/**
 * Created by huang on 2017/5/3.
 */

public class courseAdapter extends BaseAdapter {

    private List<Course> courses;
    public  courseAdapter(List<Course> list)
    {
        this.courses=list;
    }
    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
          View view=null;
      ViewHolder viewHolder;
        Course course=courses.get(position);
        if(convertView==null)
        {
            view= LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.course_item,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.courseName=(TextView)view.findViewById(R.id.coursename);
            viewHolder.courseClass=(TextView)view.findViewById(R.id.courseclass);
            viewHolder.courseRoom=(TextView)view.findViewById(R.id.courseroom);
            view.setTag(viewHolder);
        }
        else
        {
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }
        //if(course.getWeek().toString().trim().equals("周一"))
            viewHolder.courseName.setText(course.getCourse_name());
         viewHolder.courseRoom.setText(course.getCourse_addresss());
        viewHolder.courseClass.setText(course.getCourse_class());

        return view;
    }
    class ViewHolder
    {
        TextView courseName;
        TextView courseClass;
        TextView courseRoom;
    }
}
