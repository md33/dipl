package com.example.md.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView textView , textView1 ,textView2;
    int score=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        score = Integer.parseInt( getIntent().getStringExtra("score"));
        score=3;
        setContentView(R.layout.activity_result);
        textView = (TextView)findViewById(R.id.textView2);
        //textView1 = (TextView)findViewById(R.id.textView3);
        textView2 = (TextView)findViewById(R.id.textView4);
        textView.setText("Та");
        textView1.setText(String.valueOf(score));
        textView2.setText("асуултанд амжилттай хариуллаа.");

    }
}
