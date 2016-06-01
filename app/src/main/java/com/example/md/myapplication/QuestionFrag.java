package com.example.md.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.R;
import com.example.md.myapplication.db.Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class QuestionFrag extends Fragment {

    private LruCache<String, Bitmap> mMemoryCache;

    Model model;
    JSONParser jsonParser = new JSONParser();
    SimpleCursorAdapter adapter;
    List<String> listDataHeader;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> listDataChild;
    View view;
    ListView all ;
    InputStream is=null;
    int code;
    String Question;
    String line,result=null;

    public QuestionFrag() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
////        myAdapter = new MyAdapter(getContext());

        view = inflater.inflate(R.layout.fragment_question, container, false);

        all = (ListView)view.findViewById(R.id.list);
        init();
        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send();
            }
        });
        fab.setBackgroundResource(R.color.colorPrimary);


        return view;
    }
    public void send() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle("Асуулт асуух");

        final EditText Names = new EditText(getContext());
        Names.setInputType(InputType.TYPE_CLASS_TEXT);
        Names.setHint("Асуултаа бичнэ үү                        ");
        LinearLayout layout = new LinearLayout(view.getContext());

        layout.addView(Names);

        builder.setView(layout);
        builder.setPositiveButton("Асуух", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = Names.getText().toString();
                Question = name;
                //new Insert(Question).execute();
                insertToDatabase(Question);


            }
        });
        builder.setNegativeButton("Цуцлах", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void init(){


        try {
            model = new Model(view.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor =model.question();
        if (cursor != null)
        {
            if(cursor.moveToFirst()) {
                do {
                    Log.w("lol","sda"+cursor.getString(0)+
                            cursor.getString(1));
                } while (cursor.moveToNext());
            }
            adapter =
                    new SimpleCursorAdapter(getContext(),
                            R.layout.questions,
                            cursor,
                            new String[]{
                                    "_id", "question"
                            },
                            new int[]{R.id.id, R.id.question},1);

            all.setAdapter(null);
            all.setAdapter(adapter);
            all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    TextView c = (TextView) view.findViewById(R.id.id);
                    String send = c.getText().toString();
                    Toast.makeText(getContext(),send,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.setClassName("com.example.md.myapplication", "com.example.md.myapplication.question_Answer");
                    intent.putExtra("id", send);
                    startActivity(intent);


                }
            });
        }
    }

    class Insert extends AsyncTask<String, String, String> {
        String quesion;
        public Insert(String question) {
            this.quesion = question;
        }

        /**
         * Before starting background thread Show Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating product
         */
        protected String doInBackground(String... args) {
            String url = "http://192.168.1.115/webservice/Question.php";

            if(Question !=null) {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("question", this.quesion));

                // getting JSON Object
                // Note that create product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(url,
                        "POST", params);

                // check log cat fro response
                Log.w("lol", json.toString());

                // check for success tag
                try {
                    int success = json.getInt("success");

                    if (success == 1) {
                        // successfully created product
                        Toast.makeText(getContext(), "inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        // failed to create product
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done

        }
    }
    private void insertToDatabase(String question){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {
                String question = params[0];
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("question", question));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://10.0.52.147/webservice/Questions.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    //is = entity.getContent();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(question);
    }
    }


