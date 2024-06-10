package com.example.hanxu5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hanxu5.db.BooksDAO;
import com.example.hanxu5.R;
import com.example.hanxu5.bean.Book;

public class AddbooksActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentid;
    private EditText etStudentname;
    private EditText etMajoy;
    private EditText etBooknum;

    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbooks);
        initView();
    }

    private void initView() {
        etStudentid=(EditText)findViewById(R.id.et_studentid);
        etStudentname = (EditText) findViewById(R.id.et_studentname);
        etMajoy = (EditText) findViewById(R.id.et_majoy);
        etBooknum = (EditText) findViewById(R.id.et_booknum);

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String studentid=etStudentid.getText().toString().trim();
        String studentname = etStudentname.getText().toString().trim();
        String majoy = etMajoy.getText().toString().trim();
        String booknum = etBooknum.getText().toString();


        if (TextUtils.isEmpty(studentid)) {
            Toast.makeText(this, "请输入学号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(studentname)) {
            Toast.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(majoy)) {
            Toast.makeText(this, "请输入专业", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(booknum)) {
            Toast.makeText(this, "请输入座位号", Toast.LENGTH_SHORT).show();
            return;
        }

        Book o =new Book();
        o.studentid= studentid;
        o.studentname = studentname;
        o.majoy = majoy;
        o.booknum= booknum;

        BooksDAO dao = new BooksDAO(getApplicationContext());
        dao.open();
        long result = dao.addBooks(o);

        if (result > 0) {
            Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
        finish();

    }
}