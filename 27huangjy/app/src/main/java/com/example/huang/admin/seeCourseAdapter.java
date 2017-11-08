package com.example.huang.admin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.Student;

import java.util.List;


/**
 * Created by huang on 2017/5/3.
 */

public class seeCourseAdapter extends BaseAdapter{

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
        private TextView t5;
    private TextView t6;
    private TextView t7;
        private ImageButton edit;
        private  ImageButton delete;
        private List<Course> list;
        public seeCourseAdapter(List<Course> courses)
        {
            this.list=courses;
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
           final View view;
            if(convertView==null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.cou_item,null);
            }
            else
            {
                view=convertView;
            }
            final Course course=list.get(position);
            t1=(TextView) view.findViewById(R.id.cid);
            t2=(TextView) view.findViewById(R.id.cname);
            t3=(TextView) view.findViewById(R.id.croom);
            t4=(TextView) view.findViewById(R.id.teaname);
            t5=(TextView) view.findViewById(R.id.cweek);
            t6=(TextView) view.findViewById(R.id.cjieshu);
            t7=(TextView) view.findViewById(R.id.cclass);
            edit=(ImageButton) view.findViewById(R.id.s_edit);
            delete=(ImageButton)view.findViewById(R.id.sdelete);
            t1.setText(course.getCourse_id()+"");
            t2.setText(course.getCourse_name());
         //   Toast.makeText(MyApplication.getContext(),"student"+student.getMajor()+student.getId(),Toast.LENGTH_SHORT).show();
            t3.setText(course.getCourse_addresss());
            t4.setText(course.getTea_name());
            t5.setText(course.getWeek());
            t6.setText(course.getJieshu());
            t7.setText(course.getCourse_class());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MyApplication.getContext(),EditCourse.class);
                    intent.putExtra("id",course.getCourse_id()+"");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",course.getCourse_name());
                    intent.putExtra("tname",course.getTea_name());
                    intent.putExtra("class",course.getCourse_class());
                    intent.putExtra("week",course.getWeek());
                    intent.putExtra("jieshu",course.getJieshu());
                    intent.putExtra("room",course.getCourse_addresss());
                    view.getContext().startActivity(intent);
                    Toast.makeText(MyApplication.getContext(),"edit",Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                    DBOperator dbOperator=new DBOperator(MyApplication.getContext());
                    SQLiteDatabase db=dbOperator.getDatabase(MyApplication.getContext());
                     list.remove(course);
                     db.execSQL("delete from coursetable where course_id=?",new String[]{course.getCourse_id()+""});
                     notifyDataSetChanged();
                     dbOperator.dbclose();
                    Toast.makeText(MyApplication.getContext(),"删除操作成功",Toast.LENGTH_SHORT).show();

                }
            });
            return view;
        }

    }


