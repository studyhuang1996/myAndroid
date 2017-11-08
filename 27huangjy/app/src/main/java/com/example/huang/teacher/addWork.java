package com.example.huang.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.Work;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huang on 2017/4/30.
 */

public class addWork extends Activity {

   private ListView listView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;
    private EditText editText;
    private Button workadd;
    private List<Work> works=new ArrayList<Work>();
    String  courseName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_add);
        listView=(ListView)findViewById(R.id.work_list);
        workQuery();
        workAdapter wAdapter=new workAdapter(works,addWork.this);
        listView.setAdapter(wAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


    }
    public void workQuery()
    {
        Intent intent=getIntent();
        String tid=intent.getStringExtra("id").toString().trim();
        Log.d("1",tid);
        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);
        Cursor cursor=db.rawQuery("select * from work where tea_id=?",new String[]{tid});
        if(cursor.moveToFirst())
        {
            do{
                int  stuid=cursor.getInt(cursor.getColumnIndex("stu_id"));
                int teaid=cursor.getInt(cursor.getColumnIndex("tea_id"));
                int workid=cursor.getInt(cursor.getColumnIndex("work_id"));
                String  workName=cursor.getString(cursor.getColumnIndex("work_name"));
                courseName=cursor.getString(cursor.getColumnIndex("course_name"));
                int score=cursor.getInt(cursor.getColumnIndex("work_score"));

                Work work=new Work(workid,stuid,courseName,workName,teaid,score);
                works.add(work);
            }while(cursor.moveToNext());

        }
        cursor.close();
    }

    class workAdapter extends BaseAdapter {

        private int resourceId;
        private TextView t1;
        private TextView t2;
        private TextView t3;
        private TextView t4;
        private TextView t5;
        private  TextView t6;

        private ImageButton imageButton;
        private List<Work> list;
        private DBOperator dbOperator;
        private SQLiteDatabase db;
        Context context;



        public workAdapter(List<Work> work,Context context) {
            list = work;
            this.context=context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;

            if (convertView == null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.addwork_item, null);
            } else {
                view = convertView;
            }
            final Work work = list.get(position);
            t1 = (TextView) view.findViewById(R.id.sid);
            t3 = (TextView) view.findViewById(R.id.cname);
            t4 = (TextView) view.findViewById(R.id.wname);
            t5 = (TextView) view.findViewById(R.id.tid);
            t6=(TextView) view.findViewById(R.id.sscore);

            imageButton=(ImageButton)view.findViewById(R.id.imageButton2);
            t1.setText(work.getStu_id() + "");
            t3.setText(work.getCourse_name());
            t4.setText(work.getWork_name());
            t5.setText(work.getTea_id() + "");
            t6.setText(work.getScore()+"");
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // new AddWorkDialog(context);
                    LayoutInflater factory = LayoutInflater.from(addWork.this);//提示框
                    final View view = factory.inflate(R.layout.tea_addwork_dialog, null);//这里必须是final的
                    final EditText edit=(EditText)view.findViewById(R.id.tv1);//获得输入框对象
                    new AlertDialog.Builder(addWork.this).setTitle("添加成绩")
                            .setView(view).setPositiveButton("ADD", new android.content.DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String score=edit.getText().toString().trim();
                            dbOperator=new DBOperator(MyApplication.getContext());
                            db=dbOperator.getDatabase(MyApplication.getContext());
                            db.execSQL("update work set work_score=? where work_name=? and course_name=? ",new String[]{score,work.getWork_name(),courseName});
                            notifyDataSetChanged();
                        }
                    }).setNegativeButton("CANCLE", null).create().show();
                    notifyDataSetChanged();
                } });
            notifyDataSetChanged();


    return view;
}
}

}
