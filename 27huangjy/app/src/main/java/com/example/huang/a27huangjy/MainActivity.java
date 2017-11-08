package com.example.huang.a27huangjy;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.huang.javabean.Administrate;
import com.example.huang.javabean.Student;
import com.example.huang.javabean.Teacher;
import com.example.huang.javabean.Work;
import com.example.huang.student.*;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AndroidAppUri;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;

import static com.example.huang.a27huangjy.R.id.login_btn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SQLiteDatabase db;
    private DBOperator dbOprator;
    private EditText userName;
    private EditText passwd;
    private Button login_btn;
    private RadioButton select;
    private RadioGroup rg;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);
        dbOprator = new DBOperator(this);
        db = dbOprator.getDatabase(this);
     // add();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onClick(View v) {
        // Connector.getDatabase();

        userName = (EditText) findViewById(R.id.et_user_id);
        passwd = (EditText) findViewById(R.id.et_passwd);
        //  but_login=(Button)findViewById(R.id.login_btn);
        rg = (RadioGroup) findViewById(R.id.radiogroup);
        select = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
        String pw1 = "";
        String userType = select.getText().toString().trim();
      //  Toast.makeText(MainActivity.this, "usertype:" + userType, Toast.LENGTH_LONG).show();
        String userId = userName.getText().toString().trim();
        String pass = passwd.getText().toString().trim();

        Log.d("MainActivity", "uid :" + userId);
        Log.d("MainActivity", "pass :" + pass);


        if (userType.equals("Student")) {
            Cursor cursor = db.rawQuery("select * from student where stu_id=? and stu_pw=?", new String[]{userId, pass});
            if (cursor.moveToNext()) {
              //  Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                String id = cursor.getString(cursor.getColumnIndex("stu_id")).trim();
                String pw = cursor.getString(cursor.getColumnIndex("stu_pw")).trim();
                Log.d("MainActivity", "ids" + id);
                Log.d("MainActivity", "pas" + pw);
                Intent intent = new Intent(MainActivity.this, MainStudent.class);
                intent.putExtra("stuid",id);
                intent.putExtra("stupw",pw);
                startActivity(intent);


            }
            cursor.close();

        } else if (userType.equals("Teacher")) {
            Cursor cursor = db.rawQuery("select * from teacher where tea_id=? and tea_pw=?", new String[]{userId, pass});
            if (cursor.moveToNext()) {
                Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                String id = cursor.getString(cursor.getColumnIndex("tea_id")).trim();
                pw1 = cursor.getString(cursor.getColumnIndex("tea_pw")).trim();
                Log.d("MainActivity", "idt:" + id);
                Log.d("MainActivity", "pat:" + pw1);
                Intent intent = new Intent(MainActivity.this, MainTeacher.class);
                intent.putExtra("teaid", id);
                intent.putExtra("teapw", pw1);
                startActivity(intent);
            }
            cursor.close();
        } else {
            Toast.makeText(MainActivity.this, "密码错误" + pw1, Toast.LENGTH_SHORT).show();
        }


    }

    public void entry(View v) {

        Intent admin = new Intent(MainActivity.this, Admin.class);
        startActivity(admin);
    }

    //插入学生信息
    public void add() {
      /* ArrayList<Student> students = new ArrayList<Student>();
        Student s1 = new Student(10001, "huang", "123456", "女", "/bin", "1402","软件工程");
        //s1.setId(10001);
        Student s2 = new Student(10002, "liu", "123456", "男", "/bin", "1402","软件工程");
        Student s3 = new Student(10003, "yang", "123456", "男", "/bin", "1402","软件工程");
        Student s4 = new Student(10004, "ren", "123456", "女", "/bin", "1402","软件工程");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        dbOprator.add(students);*/

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        Teacher t1 = new Teacher(314890, "诸葛亮", "314890", "男", "/bin", "1402", "Android");
        // t1.setId(314890);
        Teacher t2 = new Teacher(314891, "虞姬", "314891", "女", "/bin", "1403","Android");
        teachers.add(t1);
        teachers.add(t2);
        dbOprator.teaAdd(teachers);




    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
