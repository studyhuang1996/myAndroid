package com.example.huang.admin;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MainActivity;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/2.
 */

public class ModPasswd extends Fragment {
   private View view;
    private EditText first;
    private EditText second;
    private Button modify;
    private SQLiteDatabase db;
    private DBOperator dbOprator;
   String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     super.onCreateView(inflater, container, savedInstanceState);
        view=inflater.inflate(R.layout.modifypw_admin,container,false);

        adminIndex adminActivity=(adminIndex)getActivity();
       id=(adminActivity.id).toString().trim();
        String name=(adminActivity.name).toString().trim();
        first=(EditText)view.findViewById(R.id.firstpw);
        second=(EditText)view.findViewById(R.id.secondpw);
        modify=(Button)view.findViewById(R.id.modify);

        TextView textView=(TextView)view.findViewById(R.id.textView5);
        textView.setText("欢迎 "+id+" 管理员"+name);

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firpw=first.getText().toString().trim();
                String secpw=second.getText().toString().trim();
                dbOprator = new DBOperator(MyApplication.getContext());
                db = dbOprator.getDatabase(MyApplication.getContext());
                if(firpw.equals(secpw))
                {
                    db.execSQL("update admin set admin_pw=? where admin_id=?",new String[]{firpw,id});
                  Toast.makeText(MyApplication.getContext(),"更新成功",Toast.LENGTH_SHORT).show();
                   db.close();
                    Intent intent= new Intent(MyApplication.getContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MyApplication.getContext(),"两次密码不一致",Toast.LENGTH_SHORT).show();
                    first.setText("");
                    second.setText("");
                }
            }
        });

        return view;
    }
}
