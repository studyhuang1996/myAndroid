package com.example.huang.javabean;

/**
 * Created by huang on 2017/4/28.
 */

public class Teacher {

    private int id;
    private String name;
    private String sex;
    private String passwd;
    private String tea_photo;
    private String className;
    private String tea_major;
    public Teacher(int id,String name,String passwd,String sex,String tea_photo,String className,String tea_major)
    {
          this.id=id;
        this.name=name;
        this.passwd=passwd;
        this.sex=sex;
        this.tea_photo=tea_photo;
        this.className=className;
        this.tea_major=tea_major;
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
    public void setTeaPhoto(String tea_Photo)
    {
        this.tea_photo=tea_Photo;
    }
    public String getTeaPhoto()
    {
        return tea_photo;
    }

    public void setclassName(String className)
    {
        this.className=className;
    }
    public String getclassName()
    {
        return className;
    }
    public String getTea_major()
    {
        return tea_major;
    }
}
