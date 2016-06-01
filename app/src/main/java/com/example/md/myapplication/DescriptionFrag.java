package com.example.md.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;

/**
 * Created by md_moogii0306 on 5/31/2016.
 */
public class DescriptionFrag extends Fragment {
    Model model;
    View view;
    String name;
    String large;
    int id,extra_id = 0,example_id=0;
    TextView largetext;
    LinearLayout desc;
    DescriptionFrag(String name){
        this.name= name;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.activity_description, container, false);
        desc = (LinearLayout)view.findViewById(R.id.desc);
        largetext=(TextView)view.findViewById(R.id.large);
        return view;
    }
    public void init(){
//        Toast.makeText(this,"name = " +name ,Toast.LENGTH_LONG).show();
        try {
            model = new Model(view.getContext());
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
                TextView textView = new TextView(view.getContext());
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
                TextView textView = new TextView(view.getContext());
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
                TextView textView = new TextView(view.getContext());
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
                TextView textView2 = new TextView(view.getContext());
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
