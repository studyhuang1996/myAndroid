package com.example.huang.javabean;



/**
 * Created by huang on 2017/5/1.
 */

public class Course {
    private int course_id;
    private String course_name;
    private String tea_name;
    private String jieshu;
    private String week;
    private String course_class;
    private String course_addresss;

    public Course(String course_name,String jieshu,String course_addresss)
    {
        this.course_addresss=course_addresss;
        this.jieshu = jieshu;
        this.course_name = course_name;
    }
    public Course(int course_id, String course_name,String tea_name ,String jieshu, String week, String course_class,String course_addresss) {
        this.course_class = course_class;
        this.course_id = course_id;
        this.course_name = course_name;
        this.jieshu = jieshu;
        this.week = week;
        this.tea_name = tea_name;
        this.course_addresss=course_addresss;
    }

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public String getTea_name()
    {
        return  tea_name;
    }
    public String getWeek()
    {
        return week;
    }
    public String getCourse_class()
    {
        return course_class;
    }
    public String getJieshu()
    {
        return  jieshu;
    }
    public String getCourse_addresss(){
        return  course_addresss;
    }

}

