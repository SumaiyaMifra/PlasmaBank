package com.example.plasmabank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReceiverRegister extends AppCompatActivity {

    TextView textView2;
    EditText name,email,phoneno,pass,cpass,bgroup,district,nearhos;
    RadioGroup radioGroup;
    String namestr,emailstr,phonenostr,passstr,cpassstr,bgroupstr,districtstr,nearhosstr, gender = "";
    FirebaseAuth mAuth;
    DatabaseReference myRef= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_register);

        textView2=findViewById(R.id.textView2rec);
        name=findViewById(R.id.editTextTextPersonNamerec);
        email=findViewById(R.id.editTextTextEmailAddressrec);
        phoneno=findViewById(R.id.editTextPhonerec);
        pass=findViewById(R.id.passrec);
        cpass=findViewById(R.id.pass2rec);
        bgroup=findViewById(R.id.editTextTextPersonName5rec);
        district=findViewById(R.id.editTextTextPersonName6rec);
        nearhos=findViewById(R.id.editTextTextPersonName7rec);
        radioGroup=findViewById(R.id.radiorec);
        mAuth=FirebaseAuth.getInstance();

        textView2.setOnClickListener(v -> {
            startActivity(new Intent(ReceiverRegister.this,ReceiverStartPage.class));
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButtonrec){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }
            }
        });
    }

    public void onregisterclicked(View view){
        namestr=name.getText().toString();
        emailstr=email.getText().toString();
        phonenostr=phoneno.getText().toString();
        passstr=pass.getText().toString();
        cpassstr=cpass.getText().toString();
        bgroupstr=bgroup.getText().toString();
        districtstr=district.getText().toString();
        nearhosstr=nearhos.getText().toString();


        if(namestr.isEmpty()){
            name.setError("Required!");
            return;
        }
        if(emailstr.isEmpty()){
            email.setError("Required!");
            return;
        }
        if(phonenostr.isEmpty()){
            phoneno.setError("Required!");
            return;
        }
        if(passstr.isEmpty()){
            pass.setError("Required!");
            return;
        }
        if(cpassstr.isEmpty()){
            cpass.setError("Required!");
            return;
        }
        if(!passstr.equals(cpassstr)){
            cpass.setError("Password Not Same!");
            return;
        }
        if(bgroupstr.isEmpty()){
            bgroup.setError("Required!");
            return;
        }
        if(districtstr.isEmpty()){
            district.setError("Required!");
            return;
        }
        if(nearhosstr.isEmpty()){
            nearhos.setError("Required!");
            return;
        }
        if(gender.isEmpty()){
            Toast.makeText(ReceiverRegister.this,"Enter Gender",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailstr,passstr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                ProfileReceiver profileReceiver = new ProfileReceiver(namestr,emailstr,phonenostr,gender,bgroupstr,districtstr,nearhosstr);
                String uid=mAuth.getCurrentUser().getUid();

                myRef.child("receiver").child(uid).setValue(profileReceiver);
                Toast.makeText(ReceiverRegister.this,"Sign in successful!,you can log in now",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String str= "Error! "+ e.getMessage();
                Toast.makeText(ReceiverRegister.this,str,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(ReceiverRegister.this, ReceiverStartPage.class));
        finish();

    }
}