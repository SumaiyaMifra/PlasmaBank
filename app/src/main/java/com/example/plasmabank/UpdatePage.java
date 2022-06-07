package com.example.plasmabank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdatePage extends AppCompatActivity {

    EditText name, phoneno, negdate, lastdate, bgroup, district, nearhos;
    Button button;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    String namestr, phonenostr, negdatestr, lastdatestr, bgroupstr, districtstr, nearhosstr, gender = "";
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);

        button = findViewById(R.id.buttonR1updatepage);
        name = findViewById(R.id.nameupdatepage);
        phoneno = findViewById(R.id.phonenoupdatepage);
        negdate = findViewById(R.id.negdateupdatepage);
        lastdate = findViewById(R.id.lastdateupdatepage);
        bgroup = findViewById(R.id.bgroupupdatepage);
        district = findViewById(R.id.districtupdatepage);
        nearhos = findViewById(R.id.nearhosupdatepage);
        radioGroup = findViewById(R.id.radioupdatepage);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButtonupdatepage) {
                    gender = "Male";
                } else {
                    gender = "Female";
                }
            }
        });

        button.setOnClickListener(v -> {
            namestr = name.getText().toString().trim();
            phonenostr = phoneno.getText().toString().trim();
            negdatestr = negdate.getText().toString().trim();
            lastdatestr = lastdate.getText().toString().trim();
            bgroupstr = bgroup.getText().toString().trim();
            districtstr = district.getText().toString().trim();
            nearhosstr = nearhos.getText().toString().trim();


            if (namestr.isEmpty()) {
                name.setError("Required!");
                return;
            }
            if (phonenostr.isEmpty()) {
                phoneno.setError("Required!");
                return;
            }
            if (negdatestr.isEmpty()) {
                negdate.setError("Required!");
                return;
            }
            if (lastdatestr.isEmpty()) {
                lastdate.setError("Required!");
                return;
            }
            if (bgroupstr.isEmpty()) {
                bgroup.setError("Required!");
                return;
            }
            if (districtstr.isEmpty()) {
                district.setError("Required!");
                return;
            }
            if (nearhosstr.isEmpty()) {
                nearhos.setError("Required!");
                return;
            }
            if (gender.isEmpty()) {
                Toast.makeText(UpdatePage.this, "Enter Gender", Toast.LENGTH_LONG).show();
                return;
            }


            String pattren = "MM-dd-yyyy" ;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattren) ;
            Date date = null;
            try {
                date = simpleDateFormat.parse(negdatestr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar calendar= Calendar.getInstance() ;
            calendar.setTime(date);
            calendar.add(Calendar.MONTH,2);

            String endDonatestr =simpleDateFormat.format(calendar.getTime());


            String emailstr = mAuth.getCurrentUser().getEmail();
            Profile profiledonor = new Profile(namestr, emailstr, phonenostr, gender, negdatestr, lastdatestr, bgroupstr, districtstr, nearhosstr, endDonatestr);
            String uid = mAuth.getCurrentUser().getUid() ;
            myRef.child("donor").child(uid).setValue(profiledonor);
            Toast.makeText(UpdatePage.this, "UPDATED!!", Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(UpdatePage.this, MainPage.class));
        finish();

    }
}