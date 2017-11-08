package com.example.huang.student;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.admin.AddCourse;
import com.example.huang.admin.AddStudent;
import com.example.huang.admin.AddTeacher;
import com.example.huang.admin.ModPasswd;

/**
 * Created by huang on 2017/5/4.
 */

public class MainStudent extends Activity implements View.OnClickListener{
    private LinearLayout  index;
    private LinearLayout   work;
    private LinearLayout  course;
    private LinearLayout   score;
    private LinearLayout  user;

    private ImageButton IndexImg;
    private ImageButton workImg;
    private ImageButton courseImg;
    private ImageButton scoreImg;
    private ImageButton userImg;

    private Fragment index_fra;
    private Fragment work_fra;
    private Fragment course_fra;
    private Fragment score_fra;
    private Fragment user_fra;

     static String id;
   static String pw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);
        Intent intent=getIntent();
        id=intent.getStringExtra("stuid");
        pw=intent.getStringExtra("stupw");
        initView();
        initEvent();
        select(5);
    }

    public void initView()
    {
        index= (LinearLayout) findViewById(R.id.id_index);
        work= (LinearLayout) findViewById(R.id.id_work);
        course= (LinearLayout) findViewById(R.id.id_course);
         score= (LinearLayout) findViewById(R.id.id_score);
         user=(LinearLayout)findViewById(R.id.id_user);

        IndexImg = (ImageButton) findViewById(R.id.id_index_img);
        workImg= (ImageButton) findViewById(R.id.id_work_img);
        courseImg= (ImageButton) findViewById(R.id.id_course_img);
        scoreImg= (ImageButton) findViewById(R.id.id_score_img);
        userImg=(ImageButton)findViewById(R.id.id_user_img);
    }
    public void initEvent()
    {
        index.setOnClickListener(this);
        work.setOnClickListener(this);
        course.setOnClickListener(this);
        score.setOnClickListener(this);
        user.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {

       resImgs();
        switch (v.getId())
        {
            case R.id.id_index:
                select(1);
                break;
            case R.id.id_work:
                select(2);
                break;
            case R.id.id_course:
                select(3);
                break;
            case R.id.id_score:
                select(4);
                break;
            case R.id.id_user:
                select(5);
                break;
            default:
                break;

        }
    }

    public void resImgs()
    {
      //  IndexImg.setImageResource(R.drawable.ic_action_home);
        IndexImg.setImageResource(R.drawable.ic_action_home1);
        workImg.setImageResource(R.drawable.ic_action_book1);
        courseImg.setImageResource(R.drawable.ic_action_tiles_small1);
        scoreImg.setImageResource(R.drawable.ic_action_mustache1);
        userImg.setImageResource(R.drawable.ic_action_user1);
    }

    public void select(int i)
    {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        Bundle bundle=new Bundle();
        bundle.putString("stuid",id);

        hideFragment(ft);
        switch (i)
        {
            case 1:
                if (index_fra == null)
                {
                    index_fra= new stuIndexFra();
                   index_fra.setArguments(bundle);
                    ft.add(R.id.id_fragment, index_fra);
                } else
                {
                    ft.show(index_fra);
                }
                IndexImg.setImageResource(R.drawable.ic_action_home);
                break;
            case 2:
                if (work_fra== null)
                {
                    work_fra = new workFra();
                    work_fra.setArguments(bundle);
                    ft.add(R.id.id_fragment, work_fra);
                } else
                {
                    ft.show(work_fra);

                }
                workImg.setImageResource(R.drawable.ic_action_book);
                break;
            case 3:
                if (course_fra== null)
                {
                    course_fra = new courseFra();
                    course_fra.setArguments(bundle);
                   ft.add(R.id.id_fragment, course_fra);
                } else
                {
                    ft.show(course_fra);
                }
                courseImg.setImageResource(R.drawable.ic_action_tiles_small);
                break;
            case 4:
                  if(score_fra==null)
                  {
                      score_fra=new evaFra();
                      score_fra.setArguments(bundle);
                      ft.add(R.id.id_fragment,score_fra);

                  }
                else
                  {
                      ft.show(score_fra);
                  }
                scoreImg.setImageResource(R.drawable.ic_action_mustache);
                break;
            case 5:
                if(user_fra==null)
                {
                    user_fra=new stuFra();
                    user_fra.setArguments(bundle);
                    ft.add(R.id.id_fragment,user_fra);

                }
                else
                {
                    ft.show(user_fra);
                }
                userImg.setImageResource(R.drawable.ic_action_user);
                break;
            default:
                break;
        }

        ft.commit();
    }
    private void hideFragment(FragmentTransaction transaction)
    {
        if (index_fra != null)
        {
            transaction.hide(index_fra);
        }
        if (work_fra != null)
        {
            transaction.hide(work_fra);
        }
        if (course_fra != null)
        {
            transaction.hide(course_fra);
        }
        if (score_fra != null)
        {
            transaction.hide(score_fra);
        }
        if(user_fra!=null)
        {
            transaction.hide(user_fra);
        }
    }

}
