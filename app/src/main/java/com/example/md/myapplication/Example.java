package com.example.md.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.md.myapplication.db.Model;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import java.sql.SQLException;
import java.util.List;

public class Example extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    CardView ques, ans;
    TextView question, answer,scoret,current;
    Button hint;
    int score=0,currentScore = 0;
    String check;
    Model model;
    String hints;
    int answ_id;
    EditText answers;
    int example_id;
    int count,i=0;
    private RadioButton[] answerRb=null;
    private boolean answerChecked;
    List<Answer> Answer;
    RadioGroup answersRg;
    List<Answer> correctAnswer;
    List<Question> example;

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
        hint = (Button)findViewById(R.id.hints);
        question = (TextView)findViewById(R.id.ques);
//        answers=(EditText)findViewById(R.id.answer) ;
        scoret = (TextView)findViewById(R.id.score);
        scoret.setTextColor(Color.WHITE);
        current = (TextView)findViewById(R.id.current);
        current.setTextColor(Color.WHITE);
//        answer = (TextView)findViewById(R.id.ans);

        try {
            model = new Model(getBaseContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = model.count(example_id);
        if(cursor.moveToFirst()){
            do{

                Log.w("xaxa","count "+String.valueOf(cursor.getInt(0)));
                count = cursor.getInt(0);
//                Log.w("xaxa","1"+String.valueOf(cursor.getInt(1)));
  //              Log.w("xaxa","2"+String.valueOf(cursor.getInt(2)));
            }while(cursor.moveToNext());
        }
         example =
                model.getExample(example_id);
        Answer = model.getAnswer(i+1);
        correctAnswer = model.getcAnswer(i+1);
        setAll();


    }
    public void setAll(){
        if(i<=count){


        Log.w("xaxa","size"+ String.valueOf(example.size()));
        if(example.size() > 0) {
            //question.setText(example.get(1).getHint());
            Log.w("xaxa", "lolaaaa");

            Log.w("xaxa", "id" + String.valueOf(example.get(i).getId()));
            question.setText("Асуулт : "+String.valueOf(example.get(i).getNumber())+". "+example.get(i).getQuestion());
            current.setText("Асуулт : "+String.valueOf(example.get(i).getNumber())+" / "+count);
            hints =  example.get(0).getHint();
            currentScore = example.get(0).getScore();

            scoret.setText("Оноо : " + score);
             answersRg = (RadioGroup) findViewById(R.id.answersRg);
            answersRg.setOnCheckedChangeListener(this);
            this.answerChecked = false;


            answerRb = new RadioButton[Answer.size()];
            for(int i = 0; i < Answer.size(); i ++) {
                answerRb[i] = new RadioButton(this);
                answersRg.addView(answerRb[i]);
                Log.w("xaxa", answerRb[i].toString());
                answerRb[i].setText(Answer.get(i).getcharacter() + ") " + Answer.get(i).getanswer());
                answerRb[i].setTextColor(Color.BLACK);
//                if(checkedAnswerId == Answer[i].getId()) {
//                    answerRb[i].setChecked(true);
//
//                }
            }
        }

        } else {
            Intent result = new Intent(this, Result.class);
            result.putExtra("","");
            startActivity(result);
        }



        }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // TODO Auto-generated method stub
        try {
            for (int i = 0; i < Answer.size(); i++) {
                answerRb[i].setTextColor(Color.BLACK);
            }

            RadioButton checkedRd = (RadioButton) findViewById(checkedId);
            checkedRd.setTextColor(Color.parseColor("#009933"));

            for (int i = 0; i < Answer.size(); i++) {

               Toast.makeText(this,Answer.get(checkedId).getcharacter() +" = " + correctAnswer.get(i).getcharacter(),Toast.LENGTH_SHORT).show();
                if (Answer.get(checkedId).getcharacter().equals(correctAnswer.get(i).getcharacter())) {
                    Toast.makeText(this,"yeah",Toast.LENGTH_SHORT).show();

                }
            }
        }
        catch (Exception e){
            Log.w("app", e.toString());}
        this.answerChecked = true;
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
            score += currentScore;
            answers.setText("");
        }else {

            Toast.makeText(this,"lol",Toast.LENGTH_SHORT).show();
            answers.setText("");
        }
    }
    public void onClicks(View view){
        switch (view.getId()){
            case R.id.next :
                i++;
//                 check = answers.getText().toString();
//                checkAns(answ_id,check);
//                answers.setText("");

                    answersRg.removeAllViews();
                setAll();
            break;
            case R.id.hints:
                Toast.makeText(getBaseContext(),"CLicked",Toast.LENGTH_LONG).show();
               dialog(hints);
                break;

        }
    }
    public void dialog(String hint){
        Dialog dialog =new LovelyInfoDialog(this)
                .setTopColorRes(R.color.black)
                .setIcon(R.drawable.hint)
                //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                .setTitle(R.string.hinttitle)
                .setMessage(hint)
                .show();
        dialog.show();
    }
}
