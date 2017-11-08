package com.example.huang.teacher;

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
import com.example.huang.a27huangjy.MainActivity;
import com.example.huang.a27huangjy.MainTeacher;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/4/30.
 */

public class updateTea  extends Activity{

    private EditText first;
    private EditText second;
    private Button modify;
    private SQLiteDatabase db;
    private DBOperator dbOperator;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatetea);
        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);

        first=(EditText)findViewById(R.id.firstpw);
        second=(EditText)findViewById(R.id.secondpw);
        modify=(Button)findViewById(R.id.modifytea);

        Intent intent=getIntent();
         id=intent.getStringExtra("t_id").trim();

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firpw=first.getText().toString().trim();
                String secpw=second.getText().toString().trim();

                if(firpw.equals(secpw))
                {
                    db.execSQL("update teacher set tea_pw=?  where tea_id=?",new String[]{firpw,id});
                    Toast.makeText(MyApplication.getContext(),"密码修改成功",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MyApplication.getContext(), MainActivity.class);
                    startActivity(intent);
                    db.close();
                }
                else
                {
                    Toast.makeText(MyApplication.getContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                    first.setText("");
                    second.setText("");
                }
            }
        });


    }

}
