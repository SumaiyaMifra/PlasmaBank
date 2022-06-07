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

public class ReceiverStartPage extends AppCompatActivity {

    EditText email,password;
    Button button;
    TextView textview,textView2;
    String emailstr,passstr;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_start_page);

        email=findViewById(R.id.emailstartrec);
        password=findViewById(R.id.passstartrec);
        button=findViewById(R.id.buttonR1startrec);
        textview=findViewById(R.id.textView5startrec);
        textView2=findViewById(R.id.textView2startrec);
        mAuth=FirebaseAuth.getInstance();


        textview.setOnClickListener(v -> {
            startActivity(new Intent(ReceiverStartPage.this, ReceiverRegister.class));
        });

        textView2.setOnClickListener(v -> {
            startActivity(new Intent(ReceiverStartPage.this, MainActivity2.class));
        });

        button.setOnClickListener(v -> {
             emailstr=email.getText().toString();
             passstr=password.getText().toString();

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
                    Toast.makeText(ReceiverStartPage.this, "LOGIN successful!!!", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ReceiverStartPage.this, ReceiverMainPage.class));



                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ReceiverStartPage.this,"Try to LOGIN again!",Toast.LENGTH_LONG).show();

                }
            });
        });
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(ReceiverStartPage.this, MainActivity2.class));
        finish();

    }
}