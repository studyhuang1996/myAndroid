package com.example.huang.javabean;

import com.example.huang.teacher.addWork;

/**
 * Created by huang on 2017/4/30.
 */

public class Work extends addWork {

    private int id;
    private String work_name;
    private int stu_id;
    private int tea_id;
    private String course_name;
    private  int score;

    public Work( int id,int stu_id,String course_name,String name, int tea_id,int score)
    {
        this.id=id;
      this.stu_id=stu_id;
        this.work_name=name;
        this.tea_id=tea_id;
        this.course_name=course_name;
        this.score=score;
    }
    public  void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setWork_name(String name)
    {
      this.work_name=name;
    }
    public String getWork_name()
    {
     return work_name;
    }
    public void setScore(int score)
    {
        this.score=score;

    }
    public int getScore()
    {
        return  score;
    }
    public int getStu_id()
    {
        return stu_id;
    }
    public int getTea_id()
    {
        return tea_id;
    }
    public String getCourse_name()
    {
        return course_name;
    }
}
