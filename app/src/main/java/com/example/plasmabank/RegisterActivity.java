package com.example.plasmabank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText name,email,phoneno,pass,cpass,negdate,lastdate,bgroup,district,nearhos;
    TextView textView2;
    RadioGroup radioGroup;
    String namestr,emailstr,phonenostr,passstr,cpassstr,negdatestr,lastdatestr,bgroupstr,districtstr,nearhosstr, gender = "";
    FirebaseAuth mAuth;
    DatabaseReference myRef= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.editTextTextPersonName);
        email=findViewById(R.id.editTextTextEmailAddress);
        phoneno=findViewById(R.id.editTextPhone);
        pass=findViewById(R.id.pass);
        cpass=findViewById(R.id.pass2);
        negdate=findViewById(R.id.editTextTextPersonName3);
        lastdate=findViewById(R.id.editTextTextPersonName4);
        bgroup=findViewById(R.id.editTextTextPersonName5);
        district=findViewById(R.id.editTextTextPersonName6);
        nearhos=findViewById(R.id.editTextTextPersonName7);
        radioGroup=findViewById(R.id.radio);
        mAuth=FirebaseAuth.getInstance();
        textView2=findViewById(R.id.textView2donor);

        textView2.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this,StartPage.class));
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }
            }
        });


    }

    public void onregisterclicked(View view) throws ParseException {
        namestr=name.getText().toString();
        emailstr=email.getText().toString();
        phonenostr=phoneno.getText().toString();
        passstr=pass.getText().toString();
        cpassstr=cpass.getText().toString();
        negdatestr=negdate.getText().toString();
        lastdatestr=lastdate.getText().toString();
        bgroupstr=bgroup.getText().toString().toLowerCase();
        districtstr=district.getText().toString().toLowerCase();
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
        if(negdatestr.isEmpty()){
            negdate.setError("Required!");
            return;
        }

        if(lastdatestr.isEmpty()){
            lastdate.setError("Required!");
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
            Toast.makeText(RegisterActivity.this,"Enter Gender",Toast.LENGTH_LONG).show();
            return;
        }

        String pattren = "MM-dd-yyyy" ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattren) ;
        Date date =   simpleDateFormat.parse(negdatestr) ;

        Calendar calendar= Calendar.getInstance() ;
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,2);

        String endDonatestr =simpleDateFormat.format(calendar.getTime());

        //Log.d("Date finish", endDonatestr) ;

        mAuth.createUserWithEmailAndPassword(emailstr,passstr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Profile profile = new Profile(namestr,emailstr,phonenostr,gender,negdatestr,lastdatestr,bgroupstr,districtstr,nearhosstr,endDonatestr);
                String uid=mAuth.getCurrentUser().getUid();

                myRef.child("donor").child(uid).setValue(profile);
                Toast.makeText(RegisterActivity.this,"Sign in successful!,you can log in now",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String str= "Error! "+ e.getMessage();
                Toast.makeText(RegisterActivity.this,str,Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(RegisterActivity.this, StartPage.class));
        finish();

    }


}