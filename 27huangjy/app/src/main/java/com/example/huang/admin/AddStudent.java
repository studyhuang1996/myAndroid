package com.example.huang.admin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

/**
 * Created by huang on 2017/5/2.
 */

public class AddStudent extends Fragment  {
    private EditText e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private Button addstu;
    String stuId;
    String stuName;
    String courseClass;
    String stuMajor;
    String stuSex;
    int stu_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        View view=inflater.inflate(R.layout.student_add,container,false);
        e1 =(EditText) view.findViewById(R.id.e_id);
        e2 =(EditText) view.findViewById(R.id.e_name);
        e3 =(EditText) view.findViewById(R.id.e_class);
        e4 =(EditText) view.findViewById(R.id.e_major);
        e5=(EditText) view.findViewById(R.id.e_sex);
        addstu=(Button) view.findViewById(R.id.addStu);
        Button select =(Button)view.findViewById(R.id.select);
      select.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             Intent intent=new Intent(MyApplication.getContext(),selectStu.class);
             startActivity(intent);
         }
      });


      addstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stuId=e1.getText().toString().trim();
                stuName=e2.getText().toString().trim();
                courseClass=e3.getText().toString().trim();
                stuMajor=e4.getText().toString().trim();
                stuSex=e5.getText().toString().trim();
                stu_id= Integer.parseInt(stuId);
                final ArrayList<Student> students=new ArrayList<Student>();
                Student s1=new Student(stu_id,stuName,"123456",stuSex," ",courseClass,stuMajor);
                students.add(s1);
                DBOperator.add(students);
                Toast.makeText(MyApplication.getContext(),"登记成功",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.getContext(),selectStu.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
