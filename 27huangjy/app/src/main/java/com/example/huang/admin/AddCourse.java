package com.example.huang.admin;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/2.
 */

public class AddCourse extends Fragment {

    private View view;
    private EditText e1;
    private EditText e2;
    private EditText e6;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private EditText e7;
    private Button buton;
    private Button addcourse;
    private DBOperator dbOperator;
    private SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        view = inflater.inflate(R.layout.add_course, container, false);
        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());
        initView();

        buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MyApplication.getContext(), selectCourse.class);
                startActivity(intent1);
            }
        });
        addcourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String course_id = e1.getText().toString().trim();
                String course_name = e2.getText().toString().trim();
                String week = e5.getText().toString().trim();
                String jieshu = e3.getText().toString().trim();
                String shangclass = e4.getText().toString().trim();
                String classaddress = e7.getText().toString().trim();
                String teaname = e6.getText().toString().trim();
                db.execSQL("insert into coursetable(course_id,course_name,tea_name,course_class,jieshu,day,course_address) values(?,?,?,?,?,?,?)",
                        new String[]{course_id, course_name, teaname, shangclass, jieshu, week, classaddress});

                Toast.makeText(MyApplication.getContext(), "登记成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MyApplication.getContext(), selectCourse.class);
                startActivity(intent);
            }
        });
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
     //   toolbar.setOnMenuItemClickListener(onMenuItem);
        return view;
    }

   /* private Toolbar.OnMenuItemClickListener onMenuItem = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.addcourse:
                    break;
                case R.id.modifycourse:
                    break;
                case R.id.querycourse:
                    break;
                case R.id.deletecourse:
                    break;
                default:
                    break;
            }

            return true;
        }

    }
    */
        public void initView() {
            e1 = (EditText) view.findViewById(R.id.e_id);
            e2 = (EditText) view.findViewById(R.id.e_name);
            e3 = (EditText) view.findViewById(R.id.e_jieshu);
            e4 = (EditText) view.findViewById(R.id.e_shangclass);
            e5 = (EditText) view.findViewById(R.id.e_week);
            e6 = (EditText) view.findViewById(R.id.teaname);
            e7 = (EditText) view.findViewById(R.id.e_classaddress);
            addcourse = (Button) view.findViewById(R.id.addCourse);
          buton= (Button) view.findViewById(R.id.button);
        }
    }

