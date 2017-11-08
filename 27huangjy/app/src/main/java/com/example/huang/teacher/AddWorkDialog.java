package com.example.huang.teacher;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;

/**
 * Created by huang on 2017/5/14.
 */

public class AddWorkDialog extends Dialog {


    private Button positiveButton;
    private Button  negativeButton;
    private EditText addwork;
    private Context context;
    View mView;
    private DBOperator dbOperator;
    private SQLiteDatabase db;

   public AddWorkDialog(Context context) {
        super (context, R.style.CustomDialog);
        this.context = context;

        setCustomDialog ();
    }

    private void setCustomDialog() {
       mView = LayoutInflater.from (context).inflate (R.layout.tea_addwork_dialog, null);
        Log.d ("applockshow", "setCustomDialog");
        positiveButton = (Button) mView.findViewById (R.id.btn_add);
        negativeButton = (Button) mView.findViewById (R.id.btn_cancel);
        addwork=(EditText)mView.findViewById(R.id.tv1);
        dbOperator=new DBOperator(MyApplication.getContext());
        db=dbOperator.getDatabase(MyApplication.getContext());
        super.setContentView (mView);
    }

    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }



    /**
     * 确定键监听器
     * //@param listener
     */
    public void setOnPositiveListener() {
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String score=addwork.getText().toString().trim();
                Toast.makeText(MyApplication.getContext(),"S"+score,Toast.LENGTH_SHORT).show();
              // int i= Integer.parseInt(score);
              //  db.execSQL("update work set work_score=? where work_name=?",new String[]{score,work.getWork_name()});
            }
        });
    }

    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener) {
        negativeButton.setOnClickListener (listener);
    }
}
