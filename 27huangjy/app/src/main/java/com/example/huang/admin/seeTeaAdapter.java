package com.example.huang.admin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huang.a27huangjy.DBOperator;
import com.example.huang.a27huangjy.MyApplication;
import com.example.huang.a27huangjy.R;
import com.example.huang.javabean.Student;
import com.example.huang.javabean.Teacher;

import java.util.List;


/**
 * Created by huang on 2017/5/3.
 */

public class seeTeaAdapter extends BaseAdapter{

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
        private TextView t5;
        private ImageButton edit;
        private  ImageButton delete;
        private List<Teacher> list;
        public seeTeaAdapter(List<Teacher> teachers)
        {
            this.list=teachers;
        }
        @Override
        public Object getItem(int position) {

            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           final View view;
            if(convertView==null) {
                view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.stu_item,null);
            }
            else
            {
                view=convertView;
            }
            final Teacher teacher=list.get(position);
            t1=(TextView) view.findViewById(R.id.sid);
            t2=(TextView) view.findViewById(R.id.sname);
            t3=(TextView) view.findViewById(R.id.smajor);
            t4=(TextView) view.findViewById(R.id.sclass);
            t5=(TextView) view.findViewById(R.id.ssex);
            edit=(ImageButton) view.findViewById(R.id.s_edit);
            delete=(ImageButton)view.findViewById(R.id.sdelete);
            t1.setText(teacher.getId()+"");
            t2.setText(teacher.getName());
         //   Toast.makeText(MyApplication.getContext(),"student"+student.getMajor()+student.getId(),Toast.LENGTH_SHORT).show();
            t3.setText(teacher.getTea_major());
            t4.setText(teacher.getclassName());
            t5.setText(teacher.getSex());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MyApplication.getContext(),EditTea.class);
                    intent.putExtra("id",teacher.getId()+"");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",teacher.getName());
                    intent.putExtra("class",teacher.getclassName());
                    intent.putExtra("major",teacher.getTea_major());
                    intent.putExtra("sex",teacher.getSex());
                    view.getContext().startActivity(intent);
                    Toast.makeText(MyApplication.getContext(),"edit",Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                    DBOperator dbOperator=new DBOperator(MyApplication.getContext());
                    SQLiteDatabase db=dbOperator.getDatabase(MyApplication.getContext());
                     list.remove(teacher);
                     db.execSQL("delete from teacher where tea_id=?",new String[]{teacher.getId()+""});
                     notifyDataSetChanged();
                     dbOperator.dbclose();
                    Toast.makeText(MyApplication.getContext(),"删除操作成功",Toast.LENGTH_SHORT).show();

                }
            });
            return view;
        }

    }


