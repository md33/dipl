package com.example.md.myapplication;

/**
 * Created by md_moogii0306 on 4/30/2016.
 */
public class Question {
    private int id;
    private int number;
    private String question;
    private String hint;
    private int score;

    public Question(int id , int number , String question, String hint, int score){
        this.id= id;
        this.number = number;
        this.question = question;
        this.hint = hint;
        this.score = score;
    }
    public void setId(int id){
        this.id = id ;
    }
    public int getId(){
        return  this.id;
    }
    public int getNumber(){
        return this.number;
    }
    public String getQuestion(){
        return this.question;
    }
    public int getScore(){
        return this.score;
    }
    public String getHint(){
        return this.hint;
    }

}
