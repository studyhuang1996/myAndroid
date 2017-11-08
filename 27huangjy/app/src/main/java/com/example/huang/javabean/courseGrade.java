package com.example.huang.javabean;

/**
 * Created by huang on 2017/5/14.
 */

public class courseGrade {
    private int stuid;
    private int teaid;
   private String coursename;
    private int score;
    private int id;
    public  courseGrade(int id,int score,int stuid,int teaid,String coursename)
    {
        this.id=id;
        this.score=score;
        this.stuid=stuid;
        this.teaid=teaid;
        this.coursename=coursename;
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


}
