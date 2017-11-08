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

public class EditTea extends Activity  {

    private TextView e1;
    private EditText e2;
    private EditText e3;
    private EditText e4;
    private EditText e5;
    private Button editTea;
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
        setContentView(R.layout.tea_edit);
        e1 = (TextView) findViewById(R.id.e_id);
        e2 = (EditText) findViewById(R.id.e_name);
        e3 = (EditText) findViewById(R.id.e_class);
        e4 = (EditText) findViewById(R.id.e_major);
        e5 = (EditText) findViewById(R.id.e_sex);
        editTea = (Button) findViewById(R.id.editTea);
        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());
        intent = getIntent();
        stuid = intent.getStringExtra("id");
        e1.setText(stuid);
        String name = intent.getStringExtra("name");
        e2.setText(name);
        String classn = intent.getStringExtra("class");
        e3.setText(classn);
        String major = intent.getStringExtra("major");
        e4.setText(major);

        String sex=intent.getStringExtra("sex");
        e5.setText(sex);
        editTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = e2.getText().toString().trim();
                String sclass = e3.getText().toString().trim();
                String major = e4.getText().toString().trim();
                String sex = e5.getText().toString().trim();
                //    notifyDataSetChanged();
                db.execSQL("update teacher set tea_name=? ,tea_jiaoclass=?,tea_major=?,tea_sex=?  where tea_id=?",
                        new String[]{name, sclass, major, sex,stuid});
                db.close();
                Toast.makeText(MyApplication.getContext(), "更新成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditTea.this, selectTea.class);
                startActivity(intent);
            }
        });

    }

}
