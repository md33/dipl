package com.example.md.myapplication;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        TabLayout tabLayout;
        FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
        ViewPager pager;
        Model model;
    String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mode = getIntent().getStringExtra("mode");

        tabLayout= (TabLayout) findViewById(R.id.tab_layout);
        pager= (ViewPager) findViewById(R.id.view_pager);
        FragmentManager manager=getSupportFragmentManager();
        PagerAdapter adapter=new PagerAdapter(manager);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setTabsFromPagerAdapter(adapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(mode.equals("yeah"))
        {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplication());

            alertDialogBuilder.setTitle("Систем шинчлэл");
            alertDialogBuilder.setMessage("Системд шинчлэл гарсан тулд та шинчлэл хийх үү?");
            // set positive button: Yes message
            alertDialogBuilder.setPositiveButton("Тийм",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // go to a new activity of the app
                }
            });
            // set negative button: No message
            alertDialogBuilder.setNegativeButton("Үгүй",new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    // cancel the alert box and put a Toast to the user
                    dialog.cancel();
                    Toast.makeText(getApplication(), "You chose a negative answer",
                            Toast.LENGTH_LONG).show();
                }
            });
            // set neutral button: Exit the app message

            AlertDialog alertDialog = alertDialogBuilder.create();
            // show alert
            alertDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
            switch (id){
                case R.id.lesson:
                    pager.setCurrentItem(0);
                    break;
                case R.id.example:
                    pager.setCurrentItem(1);
                    break;
                case R.id.question:
                    pager.setCurrentItem(2);
                    break;
                case R.id.sett:
                    break;
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    void xaxa() throws SQLException {
        String id, chapter;
        model = new Model(this);
        Cursor cursor = model.chapter();
        if(cursor != null) {
            if(cursor.moveToFirst()) {
                do {
                    id=cursor.getString(0);
                   chapter =cursor.getString(1);
                    Log.w("xaxa",id + "/"+chapter);
                } while (cursor.moveToNext());
            }
        }
    }
}
