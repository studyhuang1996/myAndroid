package com.example.huang.a27huangjy;

import android.app.Application;
import android.content.Context;



/**
 * Created by huang on 2017/5/1.
 */

public class MyApplication extends Application {
    private  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
       // LitePalApplication.initialize(mContext);
    }
    public static Context getContext()
    {
        return  mContext;
    }
}
