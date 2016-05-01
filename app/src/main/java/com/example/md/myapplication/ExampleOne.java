package com.example.md.myapplication;

/**
 * Created by md_moogii0306 on 4/30/2016.
 */
public class ExampleOne {
    private int id;
    private int number;
    private String question;
    private String hint;
    private int score;
    private int AnswerId;

    public ExampleOne(int id , int number , String question,String hint,int score, int answerId){
        this.id= id;
        this.number = number;
        this.question = question;
        this.hint = hint;
        this.score = score;
        this.AnswerId = answerId;
    }
    public void setId(int id){
        this.id = id ;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setScore(int score){
        this.score = score;
    }
    public void setAnswerId(int answerId){
        this.AnswerId = answerId;
    }
    public void setHint(String hint){this.hint = hint;}
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
    public int getAnswerId(){
        return this.AnswerId;
    }
    public String getHint(){
        return this.hint;
    }

}
