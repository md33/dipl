package com.example.md.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Example extends AppCompatActivity {
    CardView ques, ans;
    TextView question, answer;
    Button hint;
    Model model;
    String hints;
    int answ_id;
    EditText answers;
    int example_id;
    int count,i=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        Toast.makeText(this,"ex_id ="+example_id,Toast.LENGTH_SHORT).show();
        Log.w("xaxa", "intent id  = "+getIntent().getStringExtra("id"));
        example_id = getIntent().getIntExtra("id", 0);
//        example_id = Integer.parseInt( getIntent().getStringExtra("id"));
//        example_id = getIntent().getIntExtra(0);
        Toast.makeText(this,"ex_id ="+example_id,Toast.LENGTH_SHORT).show();
        init();
    }

    public void init() {
        ques = (CardView)findViewById(R.id.view);
        ans = (CardView)findViewById(R.id.view2);
        hint = (Button)findViewById(R.id.hint);
        question = (TextView)findViewById(R.id.ques);
        answers=(EditText)findViewById(R.id.answer) ;
        answer = (TextView)findViewById(R.id.ans);
        try {
            model = new Model(getBaseContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = model.count(example_id);
        if(cursor.moveToFirst()){
            do{
                count = cursor.getInt(0);
                Log.w("xaxa","0 "+String.valueOf(cursor.getInt(0)));
//                Log.w("xaxa","1"+String.valueOf(cursor.getInt(1)));
  //              Log.w("xaxa","2"+String.valueOf(cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
        setAll(i);


    }
    public void setAll(int questionId){
        if(i<=count){
            List<ExampleOne> example =
                    model.getExample(questionId);

        Log.w("xaxa","size"+ String.valueOf(example.size()));
        if(example.size() > 0) {
            //question.setText(example.get(1).getHint());
            Log.w("xaxa", "lolaaaa");

            Log.w("xaxa", "id" + String.valueOf(example.get(0).getId()));
            Log.w("xaxa", "num" + String.valueOf(example.get(0).getNumber()));
            Log.w("xaxa", "que" + example.get(0).getQuestion());
            question.setText(example.get(0).getQuestion());
            Log.w("xaxa", "hint" + example.get(0).getHint());
            Log.w("xaxa", "score" + example.get(0).getScore());
            answ_id = example.get(0).getAnswerId();
        }
        } else {
            Intent result = new Intent(this, Result.class);
            result.putExtra("","");
            startActivity(result);
        }



        }


    public void checkAns(int id,String check){

        String answer = null;

        Cursor cursor =model.answer(id);
        if(cursor !=null){
            if(cursor.moveToFirst()){
                do{
                    answer = cursor.getString(0);
                }while (cursor.moveToNext());
            }
        }
        Log.w("xaxa","answer "+String.valueOf(answer));
        Log.w("xaxa","check "+String.valueOf(check));
        if(answer.equals(check)){
            Toast.makeText(this,"yeah",Toast.LENGTH_SHORT).show();
            answers.setText("");
        }else {

            Toast.makeText(this,"lol",Toast.LENGTH_SHORT).show();
            answers.setText("");
        }
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.next :
                i++;
                String check = answers.getText().toString();
                checkAns(answ_id,check);
                setAll(i);
            break;

        }
    }
}
