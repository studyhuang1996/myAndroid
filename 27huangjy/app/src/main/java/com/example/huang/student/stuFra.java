package com.example.huang.student;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;


import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by huang on 2017/5/14.
 */

public class stuFra extends Fragment {
   private View view;
    private DBOperator dbOperator;
    SQLiteDatabase db;
    private CircleImageView touxiang;
    private TextView stuid;
    private TextView stuname;
    private TextView stusex;
    private TextView stuclass;
    private TextView stumajor;
    private Button updastu;
     String id;
     String sid;
    String sname;
    String sphoto;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.stu_info,container,false);

        initView();

        dbOperator=new DBOperator(MyApplication.getContext());
        db=dbOperator.getDatabase(MyApplication.getContext());

        Bundle bundle=getArguments();
        id=bundle.getString("stuid");

        Cursor cursor=db.rawQuery("select * from student where stu_id=? ", new String[]{id});
        //    SimpleCursorAdapter scadapter=new SimpleCursorAdapter(this,R.layout.tea_item,cursor,null,null,0);
        if(cursor.moveToNext())
        {
            sid=cursor.getString(cursor.getColumnIndex("stu_id")).trim();
            sname=cursor.getString(cursor.getColumnIndex("stu_name")).trim();
            sphoto=cursor.getString(cursor.getColumnIndex("stu_photo")).trim();
            String sclass=cursor.getString(cursor.getColumnIndex("stu_classname")).trim();
            String  ssex=cursor.getString(cursor.getColumnIndex("stu_sex")).trim();
            String stumajo=cursor.getString(cursor.getColumnIndex("stu_major")).trim();
            stuid.setText(sid);
            stumajor.setText(stumajo);
            stuname.setText(sname);
            stuclass.setText(sclass);
            stusex.setText(ssex);

            //设置头像，传地址。
            // tea_photo.setImageResource("");
        }

        cursor.close();
        db.close();
        updastu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update =new Intent(MyApplication.getContext(),ModifyStu.class);
                update.putExtra("sid",id);
                startActivity(update);
            }
        });

        return view;
    }

    public void initView()
    {
        touxiang=(CircleImageView)view.findViewById(R.id.logo_img) ;
        stuclass=(TextView)view.findViewById(R.id.stuclass);
        stuid=(TextView)view.findViewById(R.id.stuid);
        stuname=(TextView) view.findViewById(R.id.stuname);
        stumajor=(TextView) view.findViewById(R.id.stumajor);
        stusex=(TextView) view.findViewById(R.id.stusex);
        updastu=(Button)view.findViewById(R.id.updatestu);
    }

}
