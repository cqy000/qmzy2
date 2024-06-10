package com.example.hanxu5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hanxu5.db.BooksDAO;
import com.example.hanxu5.R;
import com.example.hanxu5.bean.Book;

public class UpdateBooksActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etStudentid;
    private Button btnSearch;
    private EditText etStudentname;
    private EditText etMajoy;
    private EditText etBooknum;

    private Button btnEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_books);
        initView();
    }

    private void initView() {
        etStudentid=(EditText) findViewById(R.id.et_studentid);
        btnSearch=(Button) findViewById(R.id.btn_search);
        etStudentname=(EditText)findViewById(R.id.et_studentname);
        etMajoy=(EditText)findViewById(R.id.et_majoy);
        etBooknum=(EditText)findViewById(R.id.et_booknum);

        btnEdit= (Button) findViewById(R.id.btn_edit);
        btnSearch.setOnClickListener((View.OnClickListener) this);
        btnEdit.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_search:
                searchOrder();
                break;
            case R.id.btn_edit:
                updateOrder();
                break;
        }
    }

    private void searchOrder() {
        String studentid=etStudentid.getText().toString().trim();
        BooksDAO dao=new BooksDAO(getApplicationContext());
        dao.open();
        Book o=dao.getBooks(studentid);

        if (o.booknum==null){
            Toast.makeText(this, "没有信息", Toast.LENGTH_SHORT).show();
        }
        etStudentname.setText(o.studentname);
        etMajoy.setText(o.majoy);
        etBooknum.setText(o.booknum);

        dao.close();
    }

    private void updateOrder() {
        Book o=new Book();
        o.studentid=etStudentid.getText().toString().trim();
        o.studentname=etStudentname.getText().toString().trim();
        o.majoy=etMajoy.getText().toString().trim();
        o.booknum=etBooknum.getText().toString().trim();

        BooksDAO dao=new BooksDAO(getApplicationContext());
        dao.open();
        long result= dao.updateBooks(o);
        if(result>0) {
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
        dao.close();
    }
}
