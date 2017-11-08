package com.example.huang.javabean;

/**
 * Created by huang on 2017/5/1.
 */

public class Administrate {

    private int adminId;
    private String admin_pw;
    private String name;
    public Administrate(int adminId,String name,String admin_pw)
    {
        this.name=name;
        this.admin_pw=admin_pw;
        this.adminId=adminId;
    }
    public void setAdminId(int id)
    {
        this.adminId=id;
    }
    public int getAdminId()
    {
        return adminId;
    }
    public void setAdmin_pw(String pw)
    {
        this.admin_pw=pw;
    }
    public  String getAdmin_pw()
    {
        return admin_pw;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return name;
    }
}
