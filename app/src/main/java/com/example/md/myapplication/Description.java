package com.example.md.myapplication;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;

public class Description extends AppCompatActivity {
    Model model;
    String large;
    int id,extra_id = 0,example_id=0;
    TextView largetext;
    LinearLayout desc;
    String name;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
         desc = (LinearLayout)findViewById(R.id.desc);
        largetext=(TextView)findViewById(R.id.large);
        setSupportActionBar(toolbar);
         name = getIntent().getStringExtra("name");
            init();

//        Toast.makeText(this,large,Toast.LENGTH_LONG).show();
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setBackground(getDrawable(R.drawable.on));
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, large, Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//
//            }
//        });
    }
    public void init(){
//        Toast.makeText(this,"name = " +name ,Toast.LENGTH_LONG).show();
        SpannableString s = new SpannableString(name);
        s.setSpan(new RelativeSizeSpan(0.75f),  2, 3, 0);
        setTitle(s);
        try {
            model = new Model(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//       String resources = getResources().getString(R.string.title_activity_description);

        Cursor cursor = model.contents(name);
        if(cursor != null){
            if(cursor.moveToFirst())
            {
                do{
                    id = cursor.getInt(0);
                    large = cursor.getString(1);
                }while(cursor.moveToNext());
            }
        }

        extra_id = model.extra(id);
        if(extra_id ==0) {
            if(example_id == 0) {
                Log.w("xaxa", "extra_id = " + String.valueOf(extra_id));
                Log.w("xaxa", "texte = " + large);
                largetext.setText(large);
            }
            else {
                largetext.setText(large);
                Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(
                        Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(this);
                textView.setLayoutParams(lparams);
                textView.setText("                                         Жишээ ажиллах...");
                textView.setTextColor(Color.parseColor("#03A9F4"));
                textView.setLinkTextColor(Color.parseColor("#03A9F4"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.w("xaxa","no extra but example");
//                       Toast.makeText(getBaseContext(),"no extra but example",Toast.LENGTH_SHORT).show();
                    }
                });
                this.desc.addView(textView);
            }
        }
        else {
            if (example_id == 0) {
                Log.w("xaxa", "extra_id1 = " + String.valueOf(extra_id));
                Log.w("xaxa", "texte = " + large);
                largetext.setText(large);
                Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(
                        Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(this);
                textView.setLayoutParams(lparams);
                textView.setText("                                         Дэлгэрэнгүй...");
                textView.setTextColor(Color.parseColor("#03A9F4"));
                textView.setLinkTextColor(Color.parseColor("#03A9F4"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.w("xaxa", " extra no example" + extra_id);
//                        Toast.makeText(getBaseContext(), " extra no example", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClassName("com.example.md.myapplication", "com.example.md.myapplication.more");
                        intent.putExtra("extra", extra_id);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
                this.desc.addView(textView);
            } else {
                largetext.setText(large);
                Toolbar.LayoutParams lparams = new Toolbar.LayoutParams(
                        Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                TextView textView = new TextView(this);
                textView.setLayoutParams(lparams);
                textView.setText("                                         Дэлгэрэнгүй...");
                textView.setTextColor(Color.parseColor("#03A9F4"));
                textView.setLinkTextColor(Color.parseColor("#03A9F4"));
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.w("xaxa", "click extra but example");
//                        Toast.makeText(getBaseContext(), "click extra but example", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClassName("com.example.md.myapplication", "com.example.md.myapplication.more");
                        intent.putExtra("extra", extra_id);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
                this.desc.addView(textView);
                Toolbar.LayoutParams lparams2 = new Toolbar.LayoutParams(
                        Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                TextView textView2 = new TextView(this);
                textView2.setLayoutParams(lparams);
                textView2.setText("                                         Жишээ ажиллах...");
                textView2.setTextColor(Color.parseColor("#03A9F4"));
                textView2.setLinkTextColor(Color.parseColor("#03A9F4"));
                textView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        Log.w("xaxa", " extra click example");
//                        Toast.makeText(getBaseContext(), " extra click example", Toast.LENGTH_SHORT).show();
                        intent.setClassName("com.example.md.myapplication", "com.example.md.myapplication.more");
                        intent.putExtra("extra", extra_id);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
                this.desc.addView(textView2);
            }
            }

    }
}
