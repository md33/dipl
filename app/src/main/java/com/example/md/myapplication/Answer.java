package com.example.md.myapplication;

/**
 * Created by md_moogii0306 on 5/4/2016.
 */
public class Answer {
    public String character;
    public String answer;
    public Answer(String character , String answer){
        this.character = character;
        this.answer = answer;
    }
    public Answer(String character){
        this.character =character;
    }
    public String getcharacter(){
        return this.character;
    }
    public String getanswer(){
        return this.answer;
    }
}
