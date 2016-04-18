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
            case 3:
                frag = new QuizFrag();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
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
            case 3:
                title="Асуулт хариулт";
                break;
        }

        return title;
    }
}
