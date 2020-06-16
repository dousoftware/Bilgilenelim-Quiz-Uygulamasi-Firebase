package com.dousoftware.bilgilenelim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView t1, mCorrectShow, mWrongShow;
    Button mMainMenu, mTryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        //t1 = (TextView)findViewById(R.id.cozulen_soru_sayisi);
        mCorrectShow = (TextView)findViewById(R.id.correct_answer);
        mWrongShow = (TextView)findViewById(R.id.wrong_answer);

        mMainMenu = (Button)findViewById(R.id.main_menu);
        mTryAgain = (Button)findViewById(R.id.try_again);

        mMainMenu.setAllCaps(false);
        mTryAgain.setAllCaps(false);



        Intent intent = getIntent();

        String questionNumber = intent.getStringExtra("numberOfQuestion");
        String correct = intent.getStringExtra("correctAnswer");
        String wrong = intent.getStringExtra("wrongAnswer");

        //t1.setText(dogru+yanlis);
        mCorrectShow.setText(correct);
        mWrongShow.setText(wrong);

    }
    public void goMainMenu(View view){
        Intent mainMenu = new Intent(Result.this, MainActivity.class);
        startActivity(mainMenu);
    }
    public void goTryAgain(View view){
        Intent trayAgain = new Intent(Result.this, Exam.class);
        startActivity(trayAgain);
    }

    public void onBackPressed(){
        Intent intent = new Intent(Result.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //return;
        super.onBackPressed();
    }
}
