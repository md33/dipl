package com.example.md.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.CustomAdapter;
import com.example.md.myapplication.R;
import com.example.md.myapplication.db.DataModel;
import com.example.md.myapplication.db.Model;

import java.sql.SQLException;
import java.util.ArrayList;


public class ExampleFrag extends Fragment {
    Model model;
    TextView textView;
    Context context;
    int score = 0;
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
        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        myOnClickListener = new MyOnClickListener(getContext());
        layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        try {
            model = new Model(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        data = model.getdata();

        adapter = new CustomAdapter(data);
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
            selectItem(v);
        }

        private void selectItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView id
                    = (TextView) viewHolder.itemView.findViewById(R.id.per);
            String selectid = (String) id.getText();
            //Toast.makeText(getContext(),selectid,Toast.LENGTH_SHORT).show();
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


