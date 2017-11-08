package com.example.huang.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Work;

import java.util.List;

/**
 * Created by huang on 2017/5/5.
 */

public class stuWorkAdapter extends BaseAdapter {

    private TextView t1;
    private TextView t2;
    private TextView t3;

    private List<Work> list;

    public stuWorkAdapter(List<Work> work) {

        this.list = work;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        //  work=WorkgetItem(position);
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //super.getView(position, convertView, parent);

        View view;
        if (convertView == null) {
            view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_work_item, null);
        } else {
            view = convertView;
        }
        Work work = list.get(position);
        t1 = (TextView) view.findViewById(R.id.cname_item);
        t2 = (TextView) view.findViewById(R.id.workname_item);
        t3 = (TextView) view.findViewById(R.id.score_item);
        t1.setText(work.getCourse_name());
        t2.setText(work.getWork_name());
        t3.setText(work.getScore()+"");


        return view;
    }
}


