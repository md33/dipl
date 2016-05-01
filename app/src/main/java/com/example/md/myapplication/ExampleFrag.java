package com.example.md.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ExampleFrag extends Fragment {
    Model model;
    TextView textView;
    Context context;
    private ArrayList<DataModel> data;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;

    public ExampleFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_example, container, false);

        // Inflate the layout for this fragment
//        textView = (TextView) view.findViewById(R.id.aa);
//        context = getActivity();
//        try {
//            model = new Model(context);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        String id, chapter ,result=null;
//        Cursor cursor = model.chapter();
//        if(cursor != null) {
//            if(cursor.moveToFirst()) {
//                do {
//                    id=cursor.getString(0);
//                    chapter =cursor.getString(1);
//                    result = id + " " + chapter + "\n";
//                } while (cursor.moveToNext());
//            }
//        }

//        textView.setText(result);
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
        myOnClickListener = new MyOnClickListener(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            model = new Model(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data = model.getdata();

        adapter = new CustomAdapter(data);
//        listView.setAdapter((ListAdapter) adapter);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private  class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.name);
            TextView id
                    = (TextView) viewHolder.itemView.findViewById(R.id.per);
            String selectedName = (String) textViewName.getText();
            String selectid = (String) id.getText();
            Toast.makeText(getContext(),selectid,Toast.LENGTH_SHORT).show();
            Log.w("xaxa","id =" +selectid);
            exam(Integer.parseInt(selectid));

        }
    }
    public void exam(int id){
        Intent intent = new Intent();
        intent.setClassName("com.example.md.myapplication", "com.example.md.myapplication.Example");
        intent.putExtra("id", id);

        startActivity(intent);
    }
}


