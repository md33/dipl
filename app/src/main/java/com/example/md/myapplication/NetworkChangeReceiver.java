package com.example.md.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by md_moogii0306 on 5/3/2016.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {

    private boolean isConnected = false;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("xaxa", "Receieved notification about network status");
        isNetworkAvailable(context);
    }
    private boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        if(!isConnected){
                            Log.w("xaxa", "Now you are connected to Internet!");
                            Toast.makeText(context, "Интернетэд холбогдсон", Toast.LENGTH_SHORT).show();
                            isConnected = true;
//
//                            MainActivity bla =  new MainActivity();
//                            String serverURL = "http://";
//                            serverURL+=bla.server_ip;
//                            serverURL+="/projects/versio2_webservice/";
//
//                            String serverURL1="Restful_Service.php";
//                            new IS_NEW_VERSION(context).execute(serverURL+serverURL1);
                        }
                        return true;
                    }
                }
            }
        }
        Log.w("xaxa", "You are not connected to Internet!");
        Toast.makeText(context, "Интернетэд холбогдоогүй байна", Toast.LENGTH_SHORT).show();
        isConnected = false;
        return false;
    }
}
