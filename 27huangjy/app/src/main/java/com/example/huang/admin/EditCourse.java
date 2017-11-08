package com.example.huang.admin;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by huang on 2017/5/3.
 */

public class EditCourse extends Activity  {

    private TextView e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private EditText e6;
    private EditText e7;
    private Button editCourse;
    DBOperator dbOperator;
    SQLiteDatabase db;
    Intent intent;
    String stuid;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_edit);
        e1 = (TextView) findViewById(R.id.e_id);
        e2 = (EditText) findViewById(R.id.e_name);
        e3 = (EditText) findViewById(R.id.e_class);
        e4 = (EditText) findViewById(R.id.e_teaname);
        e5 = (EditText) findViewById(R.id.e_week);
        e6 = (EditText) findViewById(R.id.e_jieshu);
        e7 = (EditText) findViewById(R.id.e_room);
        editCourse = (Button) findViewById(R.id.editCourse);
        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());
        intent = getIntent();
        stuid = intent.getStringExtra("id");
        e1.setText(stuid);
        String name = intent.getStringExtra("name");
        e2.setText(name);
        final String classn = intent.getStringExtra("class");
        e3.setText(classn);
      final   String week = intent.getStringExtra("week");
        e5.setText(week);
     final    String jieshu=intent.getStringExtra("jieshu");
        e6.setText(jieshu);
     final String teaname=intent.getStringExtra("tname");
        e4.setText(teaname);
      final   String room =intent.getStringExtra("room");
        e7.setText(room);
        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e2.getText().toString().trim();
                String classn= e3.getText().toString().trim();
                String teaname = e4.getText().toString().trim();
                String week = e5.getText().toString().trim();
                String jieshu=e6.getText().toString().trim();
                String room=e7.getText().toString().trim();
                //    notifyDataSetChanged;
                db.execSQL("update coursetable set course_name=? ,tea_name=?,course_class=?,day=?,jieshu=?,course_address=? where course_id=?",
                        new String[]{name, teaname,classn , week,jieshu,room,stuid});
                //dbOperator.dbclose();
                Toast.makeText(MyApplication.getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditCourse.this, selectCourse.class);
                startActivity(intent);
            }
        });

    }

}
