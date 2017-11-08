package com.example.huang.student;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Work;
import com.example.huang.teacher.addWork;
import com.example.huang.teacher.workAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/4.
 */

public class workFra extends Fragment {
    View view;
    private ListView listView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    private List<Work> works=new ArrayList<Work>();
    String id;
    int sum =0;
    double aver;
    String  courseName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.stu_work,container,false);
        listView=(ListView)view.findViewById(R.id.work_list);
        TextView textView=(TextView)view.findViewById(R.id.score);
        TextView textView1=(TextView)view.findViewById(R.id.name);

       Bundle bundle=getArguments();
        id=bundle.getString("stuid");
        workQuery();
        stuWorkAdapter wAdapter=new stuWorkAdapter(works);


        listView.setAdapter(wAdapter);
        aver=  sum/(wAdapter.getCount());
         textView.setText(aver+"");
        textView1.setText(courseName);
        return view;
    }

    public void workQuery()
    {

        dbOperator=new DBOperator(MyApplication.getContext());
        db=dbOperator.getDatabase(MyApplication.getContext());
        Cursor cursor=db.rawQuery("select * from work where stu_id=?",new String[]{id});
        if(cursor.moveToFirst())
        {
            do{
                int  stuid=cursor.getInt(cursor.getColumnIndex("stu_id"));
                int teaid=cursor.getInt(cursor.getColumnIndex("tea_id"));
                int workid=cursor.getInt(cursor.getColumnIndex("work_id"));
                String  workName=cursor.getString(cursor.getColumnIndex("work_name"));
                courseName=cursor.getString(cursor.getColumnIndex("course_name"));
                int  score=cursor.getInt(cursor.getColumnIndex("work_score"));
                //  Log.d("1",workName);
                //   Log.d("1",courseName);
                //   Toast.makeText(addWork.this,"js"+stuid+teaid+workName+courseName,Toast.LENGTH_SHORT).show();
                Work work=new Work(workid,stuid,courseName,workName,teaid,score);

                sum+=score;
                work.setScore(score);
                works.add(work);
            }while(cursor.moveToNext());

        }
        cursor.close();
        db.close();
    }


}
