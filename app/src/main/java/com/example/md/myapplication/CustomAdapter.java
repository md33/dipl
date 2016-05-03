package com.example.md.myapplication;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.md.myapplication.db.DataModel;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName,textViewpoint,textViewper;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.name);
            this.textViewpoint = (TextView) itemView.findViewById(R.id.point);
            this.textViewper = (TextView) itemView.findViewById(R.id.per);

        }
    }

    public CustomAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(ExampleFrag.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewiD = holder.textViewpoint;
        TextView textViewPoint = holder.textViewper;

        textViewName.setText(". " + dataSet.get(listPosition).getName());
        Log.w("xaxa",dataSet.get(listPosition).getName().toString());
        Log.w("xaxa",String.valueOf(dataSet.get(listPosition).getId()));
        Log.w("xaxa",String.valueOf(dataSet.get(listPosition).getPoint()));
        textViewPoint.setText(String.valueOf(dataSet.get(listPosition).getId()));
        textViewiD.setText(String.valueOf(dataSet.get(listPosition).getPoint()) + "%");

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
