package com.example.md.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;

public class more extends AppCompatActivity {
        Model model ;
    int id;
    String text;
    String name=null;
    TextView more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        more = (TextView)findViewById(R.id.more);
         id = getIntent().getIntExtra("extra", 0);
        name = getIntent().getStringExtra("name");
        Log.w("xaxa","id = " + String.valueOf(id));
        try {
            model = new Model(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setTitle(name);
        text = model.TextExtra(id);
        more.setText(text);
    }
}
