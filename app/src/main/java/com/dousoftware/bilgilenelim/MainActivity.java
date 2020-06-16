package com.dousoftware.bilgilenelim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goLearn(View view){
        Intent learn = new Intent(MainActivity.this, Learn.class);
        startActivity(learn);

    }
    public void goExam(View view){
        Intent exam = new Intent(MainActivity.this, Exam.class);
        startActivity(exam);

    }
    public void goContact(View view){
        Intent contact = new Intent(MainActivity.this, Contact.class);
        startActivity(contact);


    }
    public void goAbout(View view){
        Intent about = new Intent(MainActivity.this, About.class);
        startActivity(about);

    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this );
        builder.setTitle("Dikkat");
        builder.setMessage( "Uygulamadan Çıkmak istediğinize emin misiniz?" );
        builder.setCancelable( true );
        builder.setNegativeButton( "Hayır",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton( "Evet", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(0);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
