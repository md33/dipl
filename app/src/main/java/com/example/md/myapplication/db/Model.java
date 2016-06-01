package com.example.md.myapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.md.myapplication.Answer;
import com.example.md.myapplication.Question;

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
        String query = "SELECT * FROM chapters";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        return cursor;
    }
    public Cursor title(){
        String query = "SELECT * FROM titles";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        return cursor;
    }
    public int extra(int id){
        String query = "SELECT extra_id FROM extras WHERE content_id = '"+id+"'";
        Cursor cursor = dbHelper.exec(query);
        int extra_id=0;
        if(cursor != null) {
                if(cursor.moveToFirst())
                extra_id = cursor.getInt(0);

            return extra_id;
        }
        return 0;
    }
    public String TextExtra(int id){
        String extra = null;
        Log.w("xaxa","id = send =  " + String.valueOf(id));
        String query = "SELECT extra FROM extras WHERE extra_id = '"+id+"'";
        Cursor cursor = dbHelper.exec(query);
        if(cursor== null){
            return null;
        }else {
            if(cursor.moveToFirst())
                extra = cursor.getString(0);
            return extra;
        }

    }
    public Cursor contents(String title){
        String query = "Select  A.content_id, A.content FROM contents A inner join titles B WHERE A.title_id = B.title_id AND B.title = '"+title+"'";
        Cursor cursor = dbHelper.exec(query);

        if(cursor!= null){
                return cursor;
        }
        return null;
    }
    public Cursor answer(int id){
        String query = "Select eanswer FROM eanswers WHERE eanswer_id= "+id+"";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public Cursor count(int example_id){
        String query = "SELECT COUNT(number) FROM eQuestions WHERE example_id = "+example_id+"";
        Cursor cursor = dbHelper.exec(query);
        if(cursor != null){
            return cursor;
        }
        return cursor;
    }
    public List<Question> getExample(int question_id) {
        String query = "SELECT * FROM eQuestions WHERE example_id = '"+question_id+"'";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }

        List<Question> questions = new ArrayList<Question>();
        if(cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);

                Question question = new Question(id, cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
                Log.w("xaxa","id = "+id + "number = " +cursor.getInt(1)+"answer = " + cursor.getString(2) + "Hint = " + cursor.getString(3)
                +"score = " + cursor.getInt(4)
                );
                questions.add(question);
            }while (cursor.moveToNext());
        }
        return questions;
    }
    public List<Answer> getAnswer(int questionId){
        String query = "SELECT character , eAnswer  FROM eAnswers WHERE equestion_id = '"+questionId+"'";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        List<Answer> answers = new ArrayList<Answer>();
        while(cursor.moveToNext()) {
            Answer answer = new Answer(cursor.getString(0),cursor.getString(1));
            answers.add(answer);
        }
        return answers;
    }
    public List<Answer> getcAnswer(int questionId){
        String query = "SELECT character FROM correctAnswer WHERE equestion_id = '"+questionId+"'";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null) {
            return null;
        }
        List<Answer> canswers = new ArrayList<Answer>();
        while(cursor.moveToNext()) {
            Answer answer = new Answer(cursor.getString(0));
            canswers.add(answer);
        }
        return canswers;
    }
    public Cursor question(){
        String query ="SELECT question_id as _id , question FROM Questions";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null)
        {
            return null;
        }
        return cursor;
    }
    public Cursor Qanswer(int id) {
        String query = "Select A.question , B.answer FROM Questions A  INNER JOIN Answers B ON A.question_id = B.question_id WHERE A.question_id = '" + id + "'";
        Cursor cursor = dbHelper.exec(query);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Log.w("lol", "lll" + cursor.getString(0) +
                            cursor.getString(1));
                } while (cursor.moveToNext());
            }
        }
        return cursor;
    }
    public ArrayList<String> getHeader(){
        String query ="SELECt chapter FROM Chapters";
        Cursor cursor = dbHelper.exec(query);
        if(cursor == null)
        {
            return null;
        }
        ArrayList<String> headers = new ArrayList<>();
        while (cursor.moveToNext()){
            headers.add(cursor.getString(0));
//            Log.w("xaxa","lol"+headers.get(1));
        }
        Log.w("xaxa","lol"+headers.get(1));
        return headers;
    }
    public ArrayList<DataModel> getdata(){
        String query ="Select A.* , B.result FROM Examples A  INNER JOIN Results B ON A.example_id = B.example_id";
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
    public List<String> getChild(String chapter){
        Log.w("xaxa","chapter = "+ chapter);
        String query ="SELECT chapter_id  FROM chapters WHERE chapter = '"+chapter+"'";
        Cursor cursor = dbHelper.exec(query);
        if(cursor.moveToFirst()) {
            Log.w("xaxa", "id " + String.valueOf(cursor.getString(0)));
        }
        int id = cursor.getInt(0);
        Log.w("xaxa","id "+ String.valueOf(cursor.getString(0)));
        String query1 = "SELECt title FROM titles WHERE chapter_id = "+id+"";
        Cursor cursor1 = dbHelper.exec(query1);
        if(cursor1 == null)
        {
            return null;
        }
        List<String> child = new ArrayList<String>();
        if(cursor1.moveToFirst())
            do{
        child.add(cursor1.getString(0));
            }while (cursor1.moveToNext());
        return child;
    }

}
