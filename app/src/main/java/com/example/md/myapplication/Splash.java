package com.example.md.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Splash extends AppCompatActivity {

    private static final boolean AUTO_HIDE = true;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private NetworkChangeReceiver receiver;
    private View mContentView;
    int Check=0;
    Model model;
    String JSON;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    private TextView title, title1;
    Boolean isInternetPresent = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        try {
            model = new Model(getBaseContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
//        findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
        title = (TextView)findViewById(R.id.fullscreen_content);
        title.setText("Өгөгдлийн нууцлал");
        title1 = (TextView)findViewById(R.id.bottomtitle);
        title1.setText("аюулгүй байдал");
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        new CountDownTimer(4000, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
                progressBar.setVisibility(View.VISIBLE);
                NetworkChangeReceiver cd = new NetworkChangeReceiver(getApplicationContext());
                isInternetPresent = cd.isConnectingToInternet();
                if (isInternetPresent) {
                    // Internet Connection is Present
                    // make HTTP requests
                    Toast.makeText(getApplication(),"yeah",Toast.LENGTH_SHORT).show();
                    getDAta();
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    Toast.makeText(getApplication(),"bad",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent.setClassName("com.example.md.myapplication","com.example.md.myapplication.MainActivity");
                intent.putExtra("mode","true");
                startActivity(intent);
                finish();
            }
        }.start();
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    public void getDAta(){
        Check = model.version();
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                DefaultHttpClient httpclient = new DefaultHttpClient(new BasicHttpParams());
                HttpPost httppost = new HttpPost("http://192.168.1.2/webservice/updates.php");


                // Depends on your web service
                httppost.setHeader("Content-type", "application/json");

                InputStream inputStream = null;
                String result = null;
                try {
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    inputStream = entity.getContent();
                    // json is UTF-8 by default
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                        Log.w("lol","line "  +result);
                    }
                    result = sb.toString();
                    Log.w("lol","res "  +result);
                } catch (Exception e) {
                    // Oops
                }
                finally {
                    try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
                }
                return result;
            }
            @Override
            protected void onPostExecute(String result){
                JSON=result;
                Log.w("lol","SJON " + JSON);
                try {
                    extract(JSON);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute();
        Log.w("lol","G" + g.toString());
    }
    public void extract(String Json) throws JSONException {
        Log.w("lol","xaxaxaxa");
        String id=null;
        JSONArray Updates = null;
        if (Json != null) {
            try {
                JSONObject jsonObj = new JSONObject(Json);

                // Getting JSON Array node
                Updates = jsonObj.getJSONArray("result");
                for (int i = 0; i < Updates.length(); i++) {
                    JSONObject c = Updates.getJSONObject(i);
                     id = c.getString("update_id");
                    Log.w("lol","oject : " +id);
                    String log = c.getString("log");
                    Log.w("lol","oject log : " +log);
                    if(Check<Integer.parseInt(id)){
                        model.run(log);
                        Log.w("lol","run " + log);
                    }


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }
//        model.run("UPDATE `version` SET `last_id`='"+id.toString()+"'");


    }
}