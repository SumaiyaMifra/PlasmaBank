package com.example.plasmabank;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class MyAdapter extends FirebaseRecyclerAdapter<ReceiverModel, MyAdapter.myviewholder> {

    public MyAdapter(@NonNull FirebaseRecyclerOptions<ReceiverModel> options) {

        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull ReceiverModel model) {
        //Log.d("test",model.getname());
        holder.name.setText("Name: " + model.getname());
        holder.email.setText("email: " + model.getemail());
        holder.phoneno.setText("Phone Number: " + model.getphoneno());
        holder.negdate.setText("Negative Date: " + model.getnegdate());
        holder.lastdate.setText("Donated Date: " + model.getlastdonate());
        holder.bgroup.setText("Blood Group: " + model.getbgroup());
        holder.district.setText("District: " + model.getdistrict());
        holder.nearhos.setText("Nearby Hospital: " + model.getnearhos());
        holder.gender.setText("Gender: " + model.getGender());



        String endDonte = model.getEndDonate();
        String pattren = "MM-dd-yyyy" ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattren) ;
        Date date = null;
        try {
            date = simpleDateFormat.parse(endDonte);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Date currntDate = new Date();
        //Log.d("Cur Date", currntDate.toString());
        if (date != null) {
            long dif = currntDate.getTime() - date.getTime();
            if(dif <= 0L)
            {
                //Log.d("Date ache", dif+"") ;
                holder.name.setTextColor(Color.parseColor("#00701a"));
                holder.email.setTextColor(Color.parseColor("#00701a"));
                holder.phoneno.setTextColor(Color.parseColor("#00701a"));
                holder.negdate.setTextColor(Color.parseColor("#00701a"));
                holder.lastdate.setTextColor(Color.parseColor("#00701a"));
                holder.bgroup.setTextColor(Color.parseColor("#00701a"));
                holder.district.setTextColor(Color.parseColor("#00701a"));
                holder.nearhos.setTextColor(Color.parseColor("#00701a"));
                holder.gender.setTextColor(Color.parseColor("#00701a"));

            }
            else
            {
                //Log.d("Date over", dif+"") ;
                holder.name.setTextColor(Color.parseColor("#ab000d"));
                holder.email.setTextColor(Color.parseColor("#ab000d"));
                holder.phoneno.setTextColor(Color.parseColor("#ab000d"));
                holder.negdate.setTextColor(Color.parseColor("#ab000d"));
                holder.lastdate.setTextColor(Color.parseColor("#ab000d"));
                holder.bgroup.setTextColor(Color.parseColor("#ab000d"));
                holder.district.setTextColor(Color.parseColor("#ab000d"));
                holder.nearhos.setTextColor(Color.parseColor("#ab000d"));
                holder.gender.setTextColor(Color.parseColor("#ab000d"));

            }
        }



    }


    @NonNull

    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, email, phoneno, negdate, lastdate, bgroup, district, nearhos, gender;
        Button button;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.namerowtext);
            email = (TextView) itemView.findViewById(R.id.emailrowtext);
            phoneno = (TextView) itemView.findViewById(R.id.phonenorowtext);
            negdate = (TextView) itemView.findViewById(R.id.negdaterowtext);
            lastdate = (TextView) itemView.findViewById(R.id.lastdaterowtext);
            bgroup = (TextView) itemView.findViewById(R.id.bgrouprowtext);
            district = (TextView) itemView.findViewById(R.id.districtrowtext);
            nearhos = (TextView) itemView.findViewById(R.id.nearhosrowtext);
            gender = (TextView) itemView.findViewById(R.id.genderrowtext);
        }
    }


}

