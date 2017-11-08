package com.example.huang.teacher;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Work;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2017/5/1.
 */

public class workAdapter extends BaseAdapter {

    private int resourceId;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
    private TextView t5;
    private  TextView t6;

    private ImageButton imageButton;
    private List<Work> list;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
   Context context;



    public workAdapter(List<Work> work,Context context) {
        list = work;
        this.context=context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.addwork_item, null);
        } else {
            view = convertView;
        }
       final Work work = list.get(position);
        t1 = (TextView) view.findViewById(R.id.sid);
        //    t2=(TextView) view.findViewById(R.id.wid);
        t3 = (TextView) view.findViewById(R.id.cname);
        t4 = (TextView) view.findViewById(R.id.wname);
        t5 = (TextView) view.findViewById(R.id.tid);
        t6=(TextView) view.findViewById(R.id.sscore);
       // e1 = (EditText) view.findViewById(R.id.wscore);
        imageButton=(ImageButton)view.findViewById(R.id.imageButton2);
        t1.setText(work.getStu_id() + "");
        t3.setText(work.getCourse_name());
        t4.setText(work.getWork_name());
        t5.setText(work.getTea_id() + "");
        t6.setText(work.getScore()+"");
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // new AddWorkDialog(context);
                LayoutInflater factory = LayoutInflater.from(MyApplication.getContext());//提示框
                final View view = factory.inflate(R.layout.tea_addwork_dialog, null);//这里必须是final的
                final EditText edit=(EditText)view.findViewById(R.id.tv1);//获得输入框对象
                new AlertDialog.Builder(MyApplication.getContext()).setTitle("添加成绩")
                        .setView(view).setPositiveButton("ADD", new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // EditText  editText=new EditText(MyApplication.getContext())  ;
                        String score=edit.getText().toString().trim();
                        db.execSQL("update work set work_score=? where work_name=?",new String[]{score,work.getWork_name()});
                        notifyDataSetChanged();
                    }
                }).setNegativeButton("CANCLE", null).create().show();
            } });




        return view;
    }
}


