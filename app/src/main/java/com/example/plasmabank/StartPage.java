package com.example.plasmabank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class
StartPage extends AppCompatActivity {

    EditText email,password;
    Button button;
    TextView textview,textView2;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        email=findViewById(R.id.emailstartdonor);
        password=findViewById(R.id.passstartdonor);
        button=findViewById(R.id.buttonRstartdonor);
        textview=findViewById(R.id.textView5startdonor);
        textView2=findViewById(R.id.textView2startdonor);
        mAuth=FirebaseAuth.getInstance();



        textview.setOnClickListener(v -> {
            startActivity(new Intent(StartPage.this, RegisterActivity.class));
        });

        textView2.setOnClickListener(v -> {
            startActivity(new Intent(StartPage.this, MainActivity2.class));
        });


        button.setOnClickListener(v -> {
            String emailstr=email.getText().toString();
            String passstr=password.getText().toString();

            if(emailstr.isEmpty()){
                email.setError("Required!");
                return;
            }
            if(passstr.isEmpty()){
                password.setError("Required!");
                return;
            }

            mAuth.signInWithEmailAndPassword(emailstr,passstr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(StartPage.this, "LOGIN SUCCESSFULL!!!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(StartPage.this, MainPage.class));



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(StartPage.this,"Try to LOGIN again!",Toast.LENGTH_LONG).show();

                }
            });
        });



    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(StartPage.this, MainActivity2.class));
        finish();

    }
}