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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


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
        lol();
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
    public void lol(){
        Log.w("lol","blabla");
        String json = "{\n" +
                "    \"result\": [\n" +
                "        {\n" +
                "                \"update_id\": \"c200\",\n" +
                "                \"log\": \"Ravi Tamada\",\n" +
                "                }\n" +
                "        },\n" +
                "        {\n" +
                "                \"update_id\": \"c201\",\n" +
                "                \"log\": \"Johnny Depp\",\n" +
                "                }\n" +
                "        },\n" +
                "}";
        ArrayList<HashMap<String, String>> Updatelist;
        JSONArray Updates = null;
        Updatelist = new ArrayList<HashMap<String, String>>();
        if (json != null) {

            try {
                Log.w("lol","blabla2");
                JSONObject jsonObj = new JSONObject(json);
                Log.w("lol","blabla2");
                // Getting JSON Array node
                Updates = jsonObj.getJSONArray("result");
                for (int i = 0; i < Updates.length(); i++) {
                    JSONObject c = Updates.getJSONObject(i);
                    String id = c.getString("update_id");
                    Log.w("lol","oject : " +id);
                    String log = c.getString("log");
                    Log.w("lol","oject : " +log);
                    JSONObject update = c.getJSONObject("Update");
                    String mobile = update.getString("id");
                    Log.w("lol","id 2oject : " +mobile);
                    String home = update.getString("log");
                    Log.w("lol","log 2.2oject : " +home);
                    HashMap<String, String> updat = new HashMap<String, String>();
                    updat.put(id,mobile);

                    updat.put(log,home);
                    Log.w("lol","updat : " +updat.toString());
                    Updatelist.add(updat);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

    }
}


