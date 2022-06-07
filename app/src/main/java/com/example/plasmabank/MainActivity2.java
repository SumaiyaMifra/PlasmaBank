package com.example.plasmabank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Button button1,button2;
    TextView textView;
    AlertDialog.Builder alertdialogbuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        button1=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        textView=findViewById(R.id.textView2mainactivity2);

        textView.setOnClickListener(v -> {
            alertdialogbuilder=new AlertDialog.Builder(MainActivity2.this);
            alertdialogbuilder.setMessage("Do you want to EXIT from the app?");
            alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialogbuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog=alertdialogbuilder.create();
            alertDialog.show();


        });


        button1.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, StartPage.class));
        });

        button2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, ReceiverStartPage.class));
        });
    }
    //for back button alert message
    @Override
    public void onBackPressed() {
        alertdialogbuilder=new AlertDialog.Builder(MainActivity2.this);
        alertdialogbuilder.setMessage("Do you want to EXIT from the app?");
        alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertdialogbuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();

    }
}