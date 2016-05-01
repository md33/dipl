package com.example.md.myapplication;


public class DataModel {


    String name;
    int id ,point;
    //int image;

    public DataModel(int id , String name, int point) {
        this.id=id;
        this.name = name;
        this.point = point;
    }

    public int getId(){
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public int getPoint(){
        return this.point;
    }
}