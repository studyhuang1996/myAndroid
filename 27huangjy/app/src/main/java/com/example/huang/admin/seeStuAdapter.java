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

import java.util.List;




/**
 * Created by huang on 2017/5/3.
 */

public class seeStuAdapter extends BaseAdapter{

    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;
        private TextView t5;
        private ImageButton edit;
        private  ImageButton delete;
        private List<Student> list;
        public seeStuAdapter(List<Student> students)
        {
            this.list=students;
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
            final Student student=list.get(position);
            t1=(TextView) view.findViewById(R.id.sid);
            t2=(TextView) view.findViewById(R.id.sname);
            t3=(TextView) view.findViewById(R.id.smajor);
            t4=(TextView) view.findViewById(R.id.sclass);
            t5=(TextView) view.findViewById(R.id.ssex);
            edit=(ImageButton) view.findViewById(R.id.s_edit);
            delete=(ImageButton)view.findViewById(R.id.sdelete);
            t1.setText(student.getId()+"");
            t2.setText(student.getName());
         //   Toast.makeText(MyApplication.getContext(),"student"+student.getMajor()+student.getId(),Toast.LENGTH_SHORT).show();
            t3.setText(student.getMajor());
            t4.setText(student.getclassName());
            t5.setText(student.getSex());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MyApplication.getContext(),EditStu.class);
                    intent.putExtra("id",student.getId()+"");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("name",student.getName());
                    intent.putExtra("class",student.getclassName());
                    intent.putExtra("major",student.getMajor());
                    intent.putExtra("sex",student.getSex());
                    view.getContext().startActivity(intent);
                    Toast.makeText(MyApplication.getContext(),"edit",Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                 public void onClick(View v) {
                    DBOperator dbOperator=new DBOperator(MyApplication.getContext());
                    SQLiteDatabase db=dbOperator.getDatabase(MyApplication.getContext());
                     list.remove(student);
                     db.execSQL("delete from student where stu_id=?",new String[]{student.getId()+""});
                     notifyDataSetChanged();
                     dbOperator.dbclose();
                    Toast.makeText(MyApplication.getContext(),"删除操作成功",Toast.LENGTH_SHORT).show();

                }
            });
            return view;
        }

    }


