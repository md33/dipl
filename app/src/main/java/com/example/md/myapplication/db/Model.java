package com.example.md.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.md.myapplication.DataModel;
import com.example.md.myapplication.ExampleOne;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by md_moogii0306 on 4/23/2016.
 */
public class Model {
    private DataBaseHelper dbHelper = null;
    public Model(Context context) throws SQLException {
        dbHelper = new DataBaseHelper(context);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("Мэдээллийн сан үүсгэх боломжгүй байна");
        }
        dbHelper.openDataBase();
    }

    public Cursor chapter() {
        String query = "SELECT * FROM chapter";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        return cursor;
    }
    public Cursor title(){
        String query = "SELECT * FROM title";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        return cursor;
    }
    public Cursor content(){
        String query = "SELECT * FROM contents";
        Cursor cursor = dbHelper.exec(query);
        if(cursor!= null){
            return cursor ;

        }
        Log.w("xaxa","null" + cursor);
        return null;
    }
    public Cursor question(){
        String query = "Select * FROM equestion";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public Cursor answer(int id){
        String query = "Select eanswertext FROM eanswer WHERE eanswer_id= "+id+"";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public Cursor count(int example_id){
        String query = "SELECT COUNT(number) FROM eQuestion WHERE example_id = "+example_id+"";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public Cursor point(){
        String query = "Select * FROM points";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public List<ExampleOne> getExample(int question_id) {
        String query = "SELECT A.*, B.eAnswer_Id FROM eQuestion A INNER JOIN eAnswer B ON A.eQuestion_id = B.eQuestion_id WHERE B.equestion_id = "+question_id+" ORDER BY A.Number ASC";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }

        List<ExampleOne> questions = new ArrayList<ExampleOne>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            ExampleOne question = new ExampleOne(id, cursor.getInt(1), cursor.getString(2), cursor.getString(3),cursor.getInt(4),cursor.getInt(6));
            questions.add(question);
        }
        return questions;
    }
    public ArrayList<DataModel> getdata(){
        String query ="Select A.* , B.result FROM Example A  INNER JOIN Results B ON A.example_id = B.example_id";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null)
        {
            return null;
        }
        ArrayList<DataModel> dataModels = new ArrayList<>();
        while (cursor.moveToNext()){
            DataModel dataModel = new DataModel(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
            Log.w("xaxa","id = " +String.valueOf(dataModel.getId())+ "Name = "+String.valueOf(dataModel.getName())+"Point = "+String.valueOf(dataModel.getPoint()));
            dataModels.add(dataModel);
        }
        return dataModels;
    }
}
