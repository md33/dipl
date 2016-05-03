package com.example.md.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionFrag extends Fragment {

    private LruCache<String, Bitmap> mMemoryCache;
    GridView gd;
    Button button;
    View view;
    public QuestionFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
////        myAdapter = new MyAdapter(getContext());
         view =  inflater.inflate(R.layout.fragment_question, container, false);

//        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
        // Click action
//                Intent intent = new Intent(view.getContext(),);

//        GridView gridView = (GridView)view.findViewById(R.id.gridview);
//
//        gridView.setAdapter(new MyAdapter(view.getContext()));

        return view;

    }

}

