package com.example.huang.javabean;

/**
 * Created by huang on 2017/5/14.
 */

public class Attendance {
    private int stuid;
    private  int teaid;
    private String textname;
    private  String coursename;
    private  int score;
    private int id;
    public  Attendance(int id,int score,int stuid,int teaid,String coursename,String name)
    {
        this.id=id;
        this.score=score;
        this.stuid=stuid;
        this.teaid=teaid;
        this.coursename=coursename;
        this.textname=name;
    }
    public int getId()
    {
        return id;
    }
    public void setStuid(int stuid)
    {
        this.stuid=stuid;
    }
    public int getStuid()
    {
        return stuid;
    }
    public  void setTeaid()
    {
        this.teaid=teaid;
    }
    public int getTeaid()
    {
        return teaid;
    }
    public void setScore(int score)
    {
        this.score=score;
    }public int getScore()
    {
        return score;
    }
    public void setCoursename(String coursename)
    {
        this.coursename=coursename;
    }
    public String getCoursename()
    {
        return  coursename;
    }
    public void setTextname(String name)
    {
        this.textname=name;
    }
    public String getTextname()
    {
        return  textname;
    }


}
