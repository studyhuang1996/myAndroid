package com.example.huang.javabean;

import java.io.Serializable;

/**
 * Created by huang on 2017/4/28.
 */

public class Student implements Serializable {

    private int id;
    private String name;
    private String sex;
    private String passwd;
    private String stu_photo;
    private String className;
    private String major;
    public Student(int id,String name,String passwd,String sex,String stu_photo,String className,String major)
    {
        this.id=id;
        this.name=name;
        this.passwd=passwd;
        this.sex=sex;
        this.stu_photo=stu_photo;
        this.className=className;
        this.major=major;
    }
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
    public void setSex(String sex)
    {
        this.sex=sex;
    }
    public String getSex()
    {
        return sex;
    }

    public void setPasswd(String passwd)
    {
        this.passwd=passwd;
    }
    public String getpasswd()
    {
        return passwd;
    }
    public void setstuPhoto(String stu_Photo)
    {
        this.stu_photo=stu_Photo;
    }
    public String getstuPhoto()
    {
        return stu_photo;
    }

    public void setclassName(String className)
    {
        this.className=className;
    }
    public String getclassName()
    {
        return className;
    }
    public String getMajor()
    {
        return  major;
    }
}
