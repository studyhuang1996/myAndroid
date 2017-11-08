package com.example.huang.student;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/4.
 */

public class evaFra extends Fragment implements View.OnClickListener{

    private FragmentManager fm;
    private FragmentTransaction transaction;

    private View view;
    private LinearLayout workscore;
    private LinearLayout coursescore;
    private LinearLayout chuqin;
    private LinearLayout score;
    String id;
    private ImageButton workscore_img;
    private ImageButton coursescore_img;
    private ImageButton chuqin_img;
    private ImageButton score_img;
    Bundle bundle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      super.onCreateView(inflater, container, savedInstanceState);
         view=inflater.inflate(R.layout.stu_score,container,false);
        bundle=getArguments();
        id=bundle.getString("stuid");
         fm= getFragmentManager();
        initView();
        initEvent();
        return view;
    }

    public void initView()
    {
        workscore=(LinearLayout)view.findViewById(R.id.workscore);
        coursescore=(LinearLayout)view.findViewById(R.id.coursescore);
        chuqin=(LinearLayout)view.findViewById(R.id.chuqin);
        score=(LinearLayout)view.findViewById(R.id.score);

        workscore_img=(ImageButton)view.findViewById(R.id.workscore_img);
        coursescore_img=(ImageButton)view.findViewById(R.id.coursescore_img);
        chuqin_img=(ImageButton)view.findViewById(R.id.chuqin_img);
        score_img = (ImageButton) view.findViewById(R.id.score_img);
    }
    public void initEvent()
    {
        workscore_img.setOnClickListener(this);
        coursescore_img.setOnClickListener(this);
        chuqin_img.setOnClickListener(this);
        score_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId())
       {
           case R.id.workscore_img:
               workFra work=new workFra();

               transaction=fm.beginTransaction();
             //  work.setArguments(bundle);
               transaction.replace(R.id.id_fragment,work);
                transaction.addToBackStack(null);
               transaction.commit();
            /*  Intent work=new Intent();
               work.setClass(getActivity(),workFra.class);
               getActivity().startActivity(work);*/
               break;
           case R.id.coursescore_img:
               Intent intent3 =new Intent();
               intent3.setClass(getActivity(),stuEve.class);
               intent3.putExtra("sid",id);
               getActivity().startActivity(intent3);
               break;
           case R.id.chuqin_img:
               Intent intent1 =new Intent();
               intent1.setClass(getActivity(),stuChuqin.class);
               intent1.putExtra("sid",id);
               Toast.makeText(MyApplication.getContext(),"ids"+id,Toast.LENGTH_SHORT).show();
               getActivity().startActivity(intent1);
               break;
           case R.id.score_img:
               Intent intent2 =new Intent();
               intent2.setClass(getActivity(),qimoScore.class);
               intent2.putExtra("sid",id);
               getActivity().startActivity(intent2);
               break;
           default:
               break;

       }
    }
}
