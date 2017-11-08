package com.example.huang.teacher;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.courseGrade;

import java.util.List;

/**
 * Created by huang on 2017/5/14.
 */

public class pingjiaAdapter extends BaseAdapter {
    private  List<courseGrade> list;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private ImageView imageButton;
    private DBOperator dbOperator;
    private SQLiteDatabase db;

    public  pingjiaAdapter(List<courseGrade> grades){
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

        final courseGrade grade = list.get(position);
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
                LayoutInflater factory = LayoutInflater.from(MyApplication.getContext());//提示框
                final View view = factory.inflate(R.layout.editext_dialog, null);//这里必须是final的
                final EditText edit=(EditText)view.findViewById(R.id.editText);//获得输入框对象
                new AlertDialog.Builder(MyApplication.getContext()).setTitle("添加成绩")
                        .setView(view).setPositiveButton("ADD", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String score = edit.getText().toString().trim();
                        db.execSQL("update  set course_score=? where stu_id=?", new String[]{score, grade.getStuid() + ""});
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("CANCLE", null).create().show();
            } });

        return view;
    }
}
