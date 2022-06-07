package com.example.plasmabank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainPage extends AppCompatActivity {

    Button button1,button2,button3;
    TextView textView;
    AlertDialog.Builder alertdialogbuilder;
    FirebaseAuth mAuth;
    DatabaseReference myRef= FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        button1=findViewById(R.id.button3);
        button2=findViewById(R.id.button4);
        button3=findViewById(R.id.button5);
        textView=findViewById(R.id.textView2mainpage);
        mAuth=FirebaseAuth.getInstance();

        textView.setOnClickListener(v -> {
            alertdialogbuilder=new AlertDialog.Builder(MainPage.this);
            alertdialogbuilder.setMessage("Do you want to be logged out and go to first page?");
            alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(MainPage.this, MainActivity2.class));
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
            startActivity(new Intent(MainPage.this, UpdatePage.class));
        });

        button2.setOnClickListener(v -> {
            String uid=mAuth.getCurrentUser().getUid();

            myRef.child("donor").child(uid).removeValue();
            FirebaseUser user = mAuth.getCurrentUser();

            user.delete()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(MainPage.this, StartPage.class));
                            }
                        }
                    });



        });

        button3.setOnClickListener(v -> {
            startActivity(new Intent(MainPage.this, ReceiverMainPage.class));
        });



    }
    //for back button alert message
    @Override
    public void onBackPressed() {
        alertdialogbuilder=new AlertDialog.Builder(MainPage.this);
        alertdialogbuilder.setMessage("Do you want to be logged out and go to first page?");
        alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(MainPage.this, MainActivity2.class));
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