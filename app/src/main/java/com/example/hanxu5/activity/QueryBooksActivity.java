package com.example.hanxu5.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hanxu5.db.BooksDAO;
import com.example.hanxu5.R;

import java.util.List;
import java.util.Map;

public class QueryBooksActivity extends AppCompatActivity {
    ListView listView=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_books);
        setTitle("查看记录");
        initView();
    }

    private void initView() {
        BooksDAO dao=new BooksDAO(getApplicationContext());
        dao.open();
        List<Map<String,Object>> mOrderData=dao.getAllbooks();
        listView=(ListView)findViewById(R.id.lst_orders);
        String[] from={"studentid","studentname","majoy","booknum"};
        int[] to={R.id.tv_lst_studentid,R.id.tv_lst_studentname,R.id.tv_lst_majoy,R.id.tv_lst_booknum};
        SimpleAdapter listItemAdapter=new SimpleAdapter(QueryBooksActivity.this,mOrderData,R.layout.item_list,from,to);
        listView.setAdapter(listItemAdapter);
        dao.close();
    }
}
