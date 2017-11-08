package com.example.huang.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Student;
import com.example.huang.javabean.Teacher;

import java.util.ArrayList;

/**
 * Created by huang on 2017/5/2.
 */

public class AddTeacher extends Fragment {
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private Button addtea;
    String teaId;
    String teaName;
    String courseClass;
    String teaMajor;
    String teaSex;
    int tea_id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
          View view=inflater.inflate(R.layout.teacher_add,container,false);
        e1 =(EditText) view.findViewById(R.id.e_id);
        e2 =(EditText) view.findViewById(R.id.e_name);
        e3 =(EditText) view.findViewById(R.id.e_class);
        e4 =(EditText) view.findViewById(R.id.e_major);
        e5=(EditText) view.findViewById(R.id.e_sex);
        addtea=(Button) view.findViewById(R.id.addTea);
        Button select =(Button)view.findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyApplication.getContext(),selectTea.class);
                startActivity(intent);
            }
        });


        addtea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teaId=e1.getText().toString().trim();
                teaName=e2.getText().toString().trim();
                courseClass=e3.getText().toString().trim();
                teaMajor=e4.getText().toString().trim();
                teaSex=e5.getText().toString().trim();

                tea_id= Integer.parseInt(teaId);
                final ArrayList<Teacher> teachers=new ArrayList<Teacher>();
                Teacher t1=new Teacher(tea_id,teaName,"123456",teaSex," ",courseClass,teaMajor);
                teachers.add(t1);
                DBOperator.teaAdd(teachers);
                Toast.makeText(MyApplication.getContext(),"登记成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.getContext(),selectTea.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
