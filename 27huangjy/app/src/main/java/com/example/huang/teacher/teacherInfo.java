package com.example.huang.teacher;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by huang on 2017/4/29.
 */

public class teacherInfo extends Activity {
    private DBOperator dbOperator;
    SQLiteDatabase db;
    private CircleImageView touxiang;
    private TextView teaid;
    private TextView teaname;
    private TextView teasex;
    private TextView teaclass;
    private TextView teacourse;
    private Button updatea;
    String tea_id;
    String tea_pw;
    String tphoto;
    String tid;
    String tname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teainfo);
        initView();

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);

        Intent intent=getIntent();
        tea_id=intent.getStringExtra("tid").trim();
         tea_pw=intent.getStringExtra("tpw");
        Log.d("teainfo",tea_id);
        Cursor cursor=db.rawQuery("select * from teacher where tea_id=? ", new String[]{tea_id});
    //    SimpleCursorAdapter scadapter=new SimpleCursorAdapter(this,R.layout.tea_item,cursor,null,null,0);
        if(cursor.moveToNext())
        {
           tid=cursor.getString(cursor.getColumnIndex("tea_id")).trim();
            tname=cursor.getString(cursor.getColumnIndex("tea_name")).trim();
            tphoto=cursor.getString(cursor.getColumnIndex("tea_photo")).trim();
            String tclass=cursor.getString(cursor.getColumnIndex("tea_jiaoclass")).trim();
            String  tsex=cursor.getString(cursor.getColumnIndex("tea_sex")).trim();
            teaid.setText(tid);
            teaname.setText(tname);
            teasex.setText(tsex);
            teaclass.setText(tclass);
            //设置头像，传地址。
            // tea_photo.setImageResource("");
        }
      Cursor c2=db.rawQuery("select * from coursetable where tea_name=?",new String[]{tname});
       if(c2.moveToNext()){
           String courseName=c2.getString(c2.getColumnIndex("course_name"));
         teacourse.setText(courseName);
         }
        c2.close();
        cursor.close();
        db.close();
        updatea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update =new Intent(teacherInfo.this,updateTea.class);
                update.putExtra("teapw",tea_pw);
                update.putExtra("photo",tphoto);
                update.putExtra("t_id",tea_id);
                startActivity(update);
            }
        });

    }


    public void initView()
    {
       touxiang=(CircleImageView)findViewById(R.id.logo_img) ;
        teaclass=(TextView) findViewById(R.id.courseclass);
        teaid=(TextView) findViewById(R.id.teaid);
        teaname=(TextView) findViewById(R.id.teaname);
        teacourse=(TextView) findViewById(R.id.teacourse);
        teasex=(TextView) findViewById(R.id.teasex);
        updatea=(Button)findViewById(R.id.updatetea);
    }
}
