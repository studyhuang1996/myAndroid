package com.example.huang.teacher;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MainTeacher;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by huang on 2017/5/5.
 */

public class teaInfoFra extends Fragment {
    private DBOperator dbOperator;
    SQLiteDatabase db;
    View view;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Toast.makeText(MyApplication.getContext(),"Info",Toast.LENGTH_SHORT).show();
        view=inflater.inflate(R.layout.teainfo,container,false);
        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());
        Bundle bundle=getArguments();
        tea_id=bundle.getString("tid");

      /*  MainTeacher mainTeacher=(MainTeacher)getActivity();
       tea_id=( mainTeacher.teaid).toString().trim();
        tea_pw=(mainTeacher.tea_pw).toString().trim();*/
        initView();
        teaQery();
        return view;

    }
    public void teaQery()
    {
        Cursor cursor=db.rawQuery("select * from teacher where tea_id=? and tea_pw=?", new String[]{tea_id,tea_pw});
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
        Cursor c2=db.rawQuery("select * from course where tea_name=?",new String[]{tname});
        if(c2.moveToNext())
        {
            String courseName=c2.getString(c2.getColumnIndex("course_name"));
            teacourse.setText(courseName);
        }
        c2.close();
        cursor.close();
        db.close();
    }
    public  void initView()
    {
        touxiang=(CircleImageView)view.findViewById(R.id.logo_img) ;
        teaclass=(TextView) view.findViewById(R.id.courseclass);
        teaid=(TextView) view.findViewById(R.id.teaid);
        teaname=(TextView) view.findViewById(R.id.teaname);
        teacourse=(TextView)view.findViewById(R.id.teacourse);
        teasex=(TextView) view.findViewById(R.id.teasex);
        updatea=(Button)view.findViewById(R.id.updatetea);
    }

}
