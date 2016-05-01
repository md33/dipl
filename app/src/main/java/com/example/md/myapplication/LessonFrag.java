package com.example.md.myapplication;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class LessonFrag extends Fragment {
    Model model;
    private RecyclerView recyclerview;
    String id , chapter,t_id,title,check,c_id,content,checkr;
    public LessonFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lesson, container, false);
        recyclerview = (RecyclerView) view.findViewById(R.id.rview);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        List<ExpandableListAdapter.Item> data = new ArrayList<>();
        List<ExpandableListAdapter.Item> data1 = new ArrayList<>();
        try {
            model = new Model(view.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor= model.chapter();
        Cursor cursor1 = model.title();
        Cursor cursor2 = model.content();
                    if (cursor1.moveToFirst()) {
                        do {
                            t_id = cursor1.getString(0);
                            title = cursor1.getString(1);
                            check = cursor1.getString(2);
                                data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, t_id + " " + title));
                            if(cursor2 != null) {
                                if (cursor2.moveToFirst()) {
                                    do {
                                        c_id = cursor2.getString(0);
                                        content = cursor2.getString(1);
                                        checkr = cursor2.getString(2);
                                        if (t_id.equals(checkr))
                                            Toast.makeText(getContext(),t_id + checkr ,Toast.LENGTH_LONG).show();
                                            data.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "                "+c_id + " " + content));
                                    } while (cursor2.moveToNext());
                                }
                            }
                        } while (cursor1.moveToNext());
                    }
        recyclerview.setAdapter(new ExpandableListAdapter(data));
        return view;
    }
}
