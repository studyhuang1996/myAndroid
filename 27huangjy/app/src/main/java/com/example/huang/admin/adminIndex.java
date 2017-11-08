package com.example.huang.admin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/1.
 */

public class adminIndex extends Activity implements View.OnClickListener{
    private LinearLayout  stu;
    private LinearLayout  tea;
    private LinearLayout  course;
    private LinearLayout modifypw;

    private ImageButton StuImg;
    private ImageButton TeaImg;
    private ImageButton CourseImg;
    private ImageButton modifyPwImg;

    private Fragment stu_fra;
    private Fragment tea_fra;
    private Fragment course_fra;
    private Fragment modifypw_fra;
    String id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_index);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initEvent();
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
       name=intent.getStringExtra("name");

    }
    private void initEvent()
    {
        stu.setOnClickListener(this);
        tea.setOnClickListener(this);
       course.setOnClickListener(this);
      modifypw .setOnClickListener(this);
    }

    private void initView()
    {
          stu= (LinearLayout) findViewById(R.id.id_student);
        tea = (LinearLayout) findViewById(R.id.id_teacher);
        course= (LinearLayout) findViewById(R.id.id_course);
         modifypw = (LinearLayout) findViewById(R.id.id_pass);

        StuImg = (ImageButton) findViewById(R.id.id_student_img);
        TeaImg= (ImageButton) findViewById(R.id.id_teacher_img);
        CourseImg= (ImageButton) findViewById(R.id.id_course_img);
       modifyPwImg = (ImageButton) findViewById(R.id.id_pass_img);
    }

    private void setSelect(int i)
    {
        FragmentManager fm =getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        // 把图片设置为亮的
        // 设置内容区域
        switch (i)
        {
            case 0:
                if (stu_fra == null)
                {
                    stu_fra = new AddStudent();
                    transaction.add(R.id.id_content, stu_fra);
                } else
                {
                    transaction.show(stu_fra);
                }
                StuImg.setImageResource(R.drawable.icon_mine_selected);
                break;
            case 1:
                if (tea_fra== null)
                {
                    tea_fra = new AddTeacher();transaction.add(R.id.id_content, tea_fra);
                } else
                {
                    transaction.show(tea_fra);

                }
               TeaImg.setImageResource(R.drawable.icon_mine_selected);
                break;
            case 2:
                if (course_fra== null)
                {
                    course_fra = new AddCourse();
                    transaction.add(R.id.id_content, course_fra);
                } else
                {
                    transaction.show(course_fra);
                }
               CourseImg.setImageResource(R.drawable.icon_work_selected);
                break;
            case 3:
                if (modifypw_fra == null)
                {

                    modifypw_fra = new ModPasswd();
                    transaction.add(R.id.id_content, modifypw_fra);
                } else
                {
                    transaction.show(modifypw_fra);
                }
                modifyPwImg.setImageResource(R.drawable.icon_main_selected);
                break;

            default:
                break;
        }

        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction)
    {
        if (stu_fra != null)
        {
            transaction.hide(stu_fra);
        }
        if (tea_fra != null)
        {
            transaction.hide(tea_fra);
        }
        if (course_fra != null)
        {
            transaction.hide(course_fra);
        }
        if (modifypw_fra != null)
        {
            transaction.hide(modifypw_fra);
        }
    }

    @Override
    public void onClick(View v)
    {
        resetImgs();
        switch (v.getId())
        {
            case R.id.id_student:
                setSelect(0);
                break;
            case R.id.id_teacher:
                setSelect(1);
                break;
            case R.id.id_course:
                setSelect(2);
                break;
            case R.id.id_pass:
                setSelect(3);
                break;

            default:
                break;
        }
    }

    /**
     * 切换图片至暗色
     */
    private void resetImgs()
    {
        StuImg.setImageResource(R.drawable.icon_mine);
      TeaImg.setImageResource(R.drawable.icon_mine);
       CourseImg.setImageResource(R.drawable.icon_work);
      modifyPwImg.setImageResource(R.drawable.icon_main);
    }


}
