package com.example.huang.student;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Attendance;
import com.example.huang.javabean.Work;
import com.example.huang.javabean.courseGrade;
import com.example.huang.teacher.workAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/5/14.
 */

public class stuEve extends Activity {
   private ListView listView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    private EditText editText;
  String id;
    private List<courseGrade> grades=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stu_eve);
       /* Bundle bundle=getArguments();
        id=bundle.getString("stuid");*/
        listView=(ListView)findViewById(R.id.list);
        gradeQuery();
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

    }
    public void gradeQuery()
    {

        Intent intent = getIntent();
        String sid = intent.getStringExtra("sid").toString().trim();
        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from coursegrade where stu_id=?",new String[]{sid});
        if(cursor.moveToFirst())
        {
            do{
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                int  stuid=cursor.getInt(cursor.getColumnIndex("stu_id"));
                int teaid=cursor.getInt(cursor.getColumnIndex("tea_id"));
                String  courseName=cursor.getString(cursor.getColumnIndex("course_name"));
                int score=cursor.getInt(cursor.getColumnIndex("course_score"));
                //  Log.d("1",workName);
                //   Log.d("1",courseName);S
                 Toast.makeText(MyApplication.getContext(),"js"+stuid+teaid+courseName,Toast.LENGTH_SHORT).show();
                courseGrade work=new courseGrade(id,score,stuid,teaid,courseName);
                grades.add(work);
            }while(cursor.moveToNext());

        }
        cursor.close();
    }
    class MyAdapter extends BaseAdapter {
        // private  List<courseGrade> list;
        private TextView t1;
        private TextView t2;
        private TextView t3;
        private TextView t4;
        private TextView t5;
        private DBOperator dbOperator;
        private SQLiteDatabase db;


        @Override
        public Object getItem(int position) {
            return grades.get(position);
        }

        @Override
        public int getCount() {
            return grades.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_eve_item, null);
            } else {
                view = convertView;
            }
           final courseGrade grade = grades.get(position);
            t1 = (TextView) view.findViewById(R.id.sid);
            t2 = (TextView) view.findViewById(R.id.tid);
            t3 = (TextView) view.findViewById(R.id.sscore);
            t4=(TextView)view.findViewById(R.id.cname);
            t1.setText(grade.getStuid()+"");
            t2.setText(grade.getTeaid()+"");
            t3.setText(grade.getScore()+"");
            t4.setText(grade.getCoursename());

            return view;
        }
    }

}
