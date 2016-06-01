package com.example.md.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;

public class question_Answer extends AppCompatActivity {
    int id;
    Model model;
    TextView question, answer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question__answer);
        question = (TextView) findViewById(R.id.q);
        answer = (TextView) findViewById(R.id.a);
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        Log.w("lol", "intent id  = " + getIntent().getStringExtra("id"));
        init();
        }

        public void init(){
            Log.w("lol", "whwahhawhaawhhw");
        try {
            model = new Model(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = model.Qanswer(id);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    question.setText(cursor.getString(0));
                    answer.setText(cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
    }
    }


