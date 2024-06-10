package com.example.hanxu5.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hanxu5.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt_createe;
    private Button bt_updatee;
    private Button bt_deletee;
    private Button bt_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_createe = findViewById(R.id.bt_create);
        bt_createe.setOnClickListener(this);

        bt_updatee = findViewById(R.id.bt_update);
        bt_updatee.setOnClickListener(this);

        bt_deletee = findViewById(R.id.bt_deletee);
        bt_deletee.setOnClickListener(this);

        bt_read = findViewById(R.id.bt_read);
        bt_read.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_create){
            Intent intent = new Intent(MainActivity.this, AddbooksActivity.class);
            startActivityForResult(intent, 0);
        }else if (v.getId() == R.id.bt_update){
            Intent intent = new Intent(MainActivity.this, UpdateBooksActivity.class);
            startActivityForResult(intent, 1);
        }else if (v.getId() == R.id.bt_deletee){
            Intent intent = new Intent(MainActivity.this, DeleteBooksActivity.class);
            startActivityForResult(intent, 2);
        }else if (v.getId() == R.id.bt_read){
            Intent intent = new Intent(MainActivity.this, QueryBooksActivity.class);
            startActivityForResult(intent, 3);
        }
    }
}

