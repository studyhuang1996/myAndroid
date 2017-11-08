package com.example.huang.student;

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
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/4.
 */

public class ModifyStu extends Activity {

    private EditText first;
    private EditText second;
    private Button modify;
    private SQLiteDatabase db;
    private DBOperator dbOprator;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifypw_stu);
        first=(EditText)findViewById(R.id.firstpw);
        second=(EditText)findViewById(R.id.secondpw);
        modify=(Button)findViewById(R.id.modify);

        Intent intent=getIntent();
        id=intent.getStringExtra("sid");
        TextView textView=(TextView)findViewById(R.id.textView5);
        textView.setText("欢迎 学号为"+id+"同学");
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firpw=first.getText().toString().trim();
                String secpw=second.getText().toString().trim();
                dbOprator = new DBOperator(MyApplication.getContext());
                db = dbOprator.getDatabase(MyApplication.getContext());


                if(firpw.equals(secpw))
                {
                    db.execSQL("update student set stu_pw=? where stu_id=?",new String[]{firpw,id});
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
