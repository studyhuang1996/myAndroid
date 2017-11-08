package com.example.huang.a27huangjy;

import  com.example.huang.a27huangjy.*;
import com.example.huang.javabean.Course;
import com.example.huang.javabean.courseGrade;
import com.example.huang.teacher.CourseTable;
import com.example.huang.teacher.addWork;
import com.example.huang.teacher.chuqin;
import com.example.huang.teacher.indexAdapter;
import com.example.huang.teacher.pingjia;
import com.example.huang.teacher.teaInfoFra;
import com.example.huang.teacher.teacherInfo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by huang on 2017/4/28.
 */

public class MainTeacher extends AppCompatActivity {
    private ListView listView;
    private TextView day;
    private TextView week;
    private DrawerLayout mDraw;
    private TextView teaName;
    private TextView tea_id;
     private CircleImageView tea_photo;
    private DBOperator dbOperator;
    SQLiteDatabase db;
    String teaid;
    String name;
    public static   String tea_pw;
    String pw;
   private Fragment tea_fra;
    Bundle bundle;
    int tid;
    String weekday;
    final ArrayList<Course> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_main);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //实力控件
        day = (TextView)findViewById(R.id.day);
        week = (TextView) findViewById(R.id.weekday);

        long time = System.currentTimeMillis();
        Date date = new Date(time);
        SimpleDateFormat weeks = new SimpleDateFormat("EEEE");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        weekday = weeks.format(date);
        String today = format.format(date);
        day.setText(today);
        week.setText(weekday);

        dbOperator=new DBOperator(this);
        db=dbOperator.getDatabase(this);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        mDraw=(DrawerLayout)findViewById(R.id.draw_layout);
        NavigationView navView=(NavigationView)findViewById(R.id.teacher) ;
        navView.setItemIconTintList(null);//设置图标为本来的颜色

        //获取头布局中的姓名和工号与数据库适配
          View  headerView=navView.getHeaderView(0);
           teaName =(TextView)headerView.findViewById(R.id.tea_name);
           tea_id=(TextView)headerView.findViewById(R.id.teaId);
          tea_photo=(CircleImageView) headerView.findViewById(R.id.tea_photo);

          toolbar.setNavigationIcon(R.drawable.ic_menu);
       setSupportActionBar(toolbar);
         ActionBar actionBar=getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
             actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //设置头部信息的点击事件
         tea_query();

        coursequery();
        indexAdapter myAdapter=new indexAdapter(list);
       /* listView.setAdapter(myAdapter);
        navView.setCheckedItem(R.id.teainfo);*/
        //设置菜单的点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               mDraw.closeDrawers();
                switch (item.getItemId())
                {
                    case R.id.teainfo:

                   //tea_fra= (ListFragment)getFragmentManager().findFragmentById(R.id.tea_content);
                      /*  FragmentManager fm=getFragmentManager();
                        FragmentTransaction transaction=fm.beginTransaction();

                           if(tea_fra==null)
                           {
                              tea_fra= new teaInfoFra();
                               tea_fra.setArguments(bundle);
                               transaction.replace(R.id.id_teacontent,tea_fra);
                               Toast.makeText(MainTeacher.this,"teacherInfo",Toast.LENGTH_SHORT).show();
                           }
                        else {
                               transaction.show(tea_fra);
                           }
                        transaction.commit();*/
                       Intent  teainfo =new Intent(MainTeacher.this,teacherInfo.class);
                         teainfo.putExtra("tid",tid+"");
                        teainfo.putExtra("tpw",pw);
                        startActivity(teainfo);

                        break;
                    case R.id.teacourse:
                         Intent inten=new Intent(MainTeacher.this, CourseTable.class);
                          inten.putExtra("tid",tid+"");
                          startActivity(inten);
                        Toast.makeText(MainTeacher.this,"coursetable",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.attendance:
                        Intent inte=new Intent(MainTeacher.this, chuqin.class);
                        inte.putExtra("tid",tid+"");
                        startActivity(inte);
                        Toast.makeText(MainTeacher.this,"attendance",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.evaluate:
                        Intent in=new Intent(MainTeacher.this,pingjia.class);
                        in.putExtra("tid",tid+"");
                        startActivity(in);
                        Toast.makeText(MainTeacher.this,"evaluate",Toast.LENGTH_SHORT).show();
                        break;
                    case  R.id.work:
                        Intent work=new Intent(MainTeacher.this,addWork.class);
                        work.putExtra("id",teaid);
                        startActivity(work);
                        Toast.makeText(MainTeacher.this,"work",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                       break;
                }
                return true;
            }
        });
    }

    public void tea_query()
    {
        Intent intent=getIntent();
        teaid=intent.getStringExtra("teaid").trim();
        bundle=new Bundle();
        bundle.putString("tid",teaid);
        tea_pw=intent.getStringExtra("teapw").trim();
         tea_id.setText(teaid);
        Cursor cursor=db.rawQuery("select * from teacher where tea_id=? and tea_pw=?", new String[]{teaid,tea_pw});
        if(cursor.moveToNext())
        {
             tid=cursor.getInt(cursor.getColumnIndex("tea_id"));
            name=cursor.getString(cursor.getColumnIndex("tea_name")).trim();
            pw=cursor.getString(cursor.getColumnIndex("tea_pw")).trim();
            //   String tphoto=cursor.getString(cursor.getColumnIndex("tea_photo")).trim();

            teaName.setText(name);
            //设置头像，传地址。
           // tea_photo.setImageResource(tphoto);
        }
        cursor.close();
         dbOperator.dbclose();

    }
    public void coursequery() {


        dbOperator = new DBOperator(MyApplication.getContext());
        db = dbOperator.getDatabase(MyApplication.getContext());


        Cursor cursor = db.rawQuery("select * from  coursetable where day=?", new String[]{weekday});
        if (cursor.moveToFirst()) {
            do {

                String jie = cursor.getString(cursor.getColumnIndex("jieshu"));
                String add = cursor.getString(cursor.getColumnIndex("course_address"));
                String courseName = cursor.getString(cursor.getColumnIndex("course_name"));

                Course course = new Course(courseName, jie, add);
                list.add(course);
            } while (cursor.moveToNext());

        } else {
            Toast.makeText(MyApplication.getContext(), "今天没课", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }



}
