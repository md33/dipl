package com.example.md.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag = new LessonFrag();
                break;
            case 1:
                frag = new ExampleFrag();
                break;
            case 2:
                frag = new QuestionFrag();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Хичээл";
                break;
            case 1:
                title="Жишээ";
                break;
            case 2:
                title="Асуулт";
                break;
        }

        return title;
    }
}
