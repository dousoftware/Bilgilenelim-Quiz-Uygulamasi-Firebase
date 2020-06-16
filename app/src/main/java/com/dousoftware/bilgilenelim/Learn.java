package com.dousoftware.bilgilenelim;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.dousoftware.bilgilenelim.Model.Informations;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class Learn extends AppCompatActivity {

    private TextView mInformation;
    private Button mNewInformation;
    private AdView mAdView;
    int mNumberOfInformation = 1;
    private int say = 0;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Toolbar toolbar = findViewById(R.id.toolbar);

        mInformation = (TextView)findViewById(R.id.information);
        mNewInformation =(Button)findViewById(R.id.newInformation);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        say = 0;
        informationAnimation(mInformation, 0);
        information(rastgeleBilgi);

    }
    //Bilgi animasyonu
    public void informationAnimation(final View view, final int value){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(150)
                .setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (value == 0 && say < 1){
                    informationAnimation(mNewInformation, 0);
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
                    informationAnimation(view, 1);
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
    //Veritabanından bilgileri çekme ve rastgele olarak gösterme
    int rastgeleBilgi;
    private void information(int eskii_id){
        mNumberOfInformation++;

        Random ranB = new Random();
        rastgeleBilgi = ranB.nextInt(72)+1;

        if (rastgeleBilgi == eskii_id){
            rastgeleBilgi = ranB.nextInt(82)+1;
            information(rastgeleBilgi);
        }

        if (mNumberOfInformation == 11){
            //Toas mesajı gösterilecek.
        }else{
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Bilgilenelim").child("Bilgiler").child(String.valueOf(rastgeleBilgi));
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    final Informations informations = dataSnapshot.getValue(Informations.class);
                    mInformation.setText(informations.getBilgi());
                    mNewInformation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            say = 0;
                            informationAnimation(mInformation, 0);
                            information(rastgeleBilgi);
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


}
