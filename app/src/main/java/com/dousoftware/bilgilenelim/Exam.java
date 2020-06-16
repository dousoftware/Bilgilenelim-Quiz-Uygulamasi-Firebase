package com.dousoftware.bilgilenelim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.animation.Animator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.dousoftware.bilgilenelim.Model.Sorular;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Locale;
import java.util.Random;


public class Exam extends AppCompatActivity {


    private TextView mQuestion, mTime;
    private LinearLayout linearLayout2;
    private Button mOption1, mOption2, mOption3, mOption4, mFinishTheExam;

    private int say = 0;
    int mNumberOfQuestion = 1;
    int mCorrectAnswer = 0;
    int mWrongAnswer = 0;

    DatabaseReference databaseReference;

    private static final long START_TIME_IN_MILLIS = 15000;
    private CountDownTimer mCountDownTimer;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQuestion = findViewById(R.id.question);
        mTime = findViewById(R.id.time);
        linearLayout2 = findViewById(R.id.linearLayout2);

        mOption1 = (Button)findViewById(R.id.button);
        mOption2 = (Button)findViewById(R.id.button2);
        mOption3 = (Button)findViewById(R.id.button3);
        mOption4 = (Button)findViewById(R.id.button4);
        mFinishTheExam = (Button)findViewById(R.id.finish_the_exam);

        mOption1.setAllCaps(false);
        mOption2.setAllCaps(false);
        mOption3.setAllCaps(false);
        mOption4.setAllCaps(false);
        mFinishTheExam.setAllCaps(false);

        say = 0;
        questionAnimation(mQuestion, 0);
        question(0);



    }

    //soru animasyonu
    public void questionAnimation(final View view, final int value){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(150)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && say < 5){
                    questionAnimation(linearLayout2.getChildAt(say), 0);
                    say++;
                }
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                //data change

                if (value == 0){

                    //String data
                    //data
                  //((TextView)view).setText(data);
                    questionAnimation(view, 1);
                }
            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }
    // Soruların veritabanından çekilmesi ve rastgele olarak gösterilmesi.

    int rastgele;
    private void question(int eski_id){
        mNumberOfQuestion++;
        mTime.setText(String.valueOf(15));
        startTimer();

        Random rand = new Random();
          rastgele = rand.nextInt(79)+1;
        // bir önceki sorunun id sini almalıyız
        if(rastgele == eski_id) {
            rastgele = rand.nextInt(79)+1;
            question(rastgele);
        }

        if (mNumberOfQuestion == 11){
            mCountDownTimer.cancel();
            resultDetails();
        }else {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("VeriTabaniAdi").child("VeritabaniTabloAdi")
                    .child(String.valueOf(rastgele));
            databaseReference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Sorular sorular = dataSnapshot.getValue(Sorular.class);
                    mQuestion.setText(sorular.getSoru());
                    mOption1.setText(sorular.getSeçenek1());
                    mOption2.setText(sorular.getSeçenek2());
                    mOption3.setText(sorular.getSeçenek3());
                    mOption4.setText(sorular.getSeçenek4());

                    mOption1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resetTimer();
                            if (mOption1.getText().toString().equals(sorular.getCevap())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mCorrectAnswer++;
                                    }
                                },1500);
                            }else{
                                mWrongAnswer++;
                            }
                            say = 0;
                            questionAnimation(mQuestion, 0);
                            question(rastgele);
                        }
                    });

                    mOption2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resetTimer();
                            if (mOption2.getText().toString().equals(sorular.getCevap())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mCorrectAnswer++;
                                    }
                                },1500);
                            }else{
                                mWrongAnswer++;
                            }
                            say = 0;
                            questionAnimation(mQuestion, 0);
                            question(rastgele);

                        }
                    });

                    mOption3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resetTimer();
                            if (mOption3.getText().toString().equals(sorular.getCevap())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mCorrectAnswer++;
                                    }
                                },1500);
                            }else{
                                mWrongAnswer++;
                            }
                            say = 0;
                            questionAnimation(mQuestion, 0);
                            question(rastgele);

                        }
                    });

                    mOption4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            resetTimer();
                            if (mOption4.getText().toString().equals(sorular.getCevap())){
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mCorrectAnswer++;
                                    }
                                },1500);
                            }else{
                                mWrongAnswer++;
                            }
                            say = 0;
                            questionAnimation(mQuestion, 0);
                            question(rastgele);

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }
    //Sınav sonuçları = Soru sayısı, doğru ve yanlış cevapları gönder.
    public void resultDetails(){
        Intent intent = new Intent(Exam.this, Result.class);
        intent.putExtra("numberOfQuestion", String.valueOf(mNumberOfQuestion));
        intent.putExtra("correctAnswer", String.valueOf(mCorrectAnswer));
        intent.putExtra("wrongAnswer", String.valueOf(mWrongAnswer));
        startActivity(intent);
    }
    //Sınavı bitir butonuna basılınca olacaklar.
    public void finishTheExam(View view){
        pauseTimer();
        final AlertDialog.Builder builder = new AlertDialog.Builder( Exam.this);
        builder.setTitle("Dikkat");
        builder.setMessage( "Sınavı bitirmek istediğinize emin misiniz?" );
        builder.setCancelable( true );
        builder.setNegativeButton( "Hayır",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                startTimer();
            }
        });
        builder.setPositiveButton( "Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent examFinish = new Intent(Exam.this, Result.class);
                startActivity(examFinish);
                mCountDownTimer.cancel();
                resetTimer();
                finish();
                resultDetails();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    //Zamanlamayı başlat
    public void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                resultDetails();
            }
        }.start();
    }
    //zamanı kapat, döngüyü kır
    public void pauseTimer(){
        mCountDownTimer.cancel();
    }
    public void resetTimer(){
        mCountDownTimer.cancel();
        mTime.setText("15");
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();

    }
    //zamanı durdur ve başa al
    public void stopTimer(){
        mCountDownTimer.cancel();
        mTime.setText("15");
    }

    public void updateCountDownText(){
        //int minutes = (int)(mTimeLeftInMillis / 1000)/15;
        int seconds = (int) (mTimeLeftInMillis /1000)%15;
        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d", seconds );
        mTime.setText(timeLeftFormatted);
    }
    //Geri tuşuna basılınca olacaklar
    @Override
    public void onBackPressed() {
        pauseTimer();
        final AlertDialog.Builder builder = new AlertDialog.Builder( Exam.this );
        builder.setTitle("Dikkat");
        builder.setMessage( "Çıkarsanız sınavınız iptal edilir. Kabul ediyor musunuz?" );
        builder.setCancelable( true );
        builder.setNegativeButton( "Hayır",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                startTimer();
            }
        });
        builder.setPositiveButton( "Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mCountDownTimer.cancel();
                resetTimer();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
