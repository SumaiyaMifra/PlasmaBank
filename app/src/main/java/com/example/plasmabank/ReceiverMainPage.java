package com.example.plasmabank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class ReceiverMainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView,textView2;
    AlertDialog.Builder alertdialogbuilder;
    Toolbar toolbar;
    MyAdapter adapter;
    SearchView searchView;
    List<ReceiverModel>array=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_main_page);

        //textView=findViewById(R.id.textView2mainrec);
        //textView2=findViewById(R.id.textView4mainrec);
        //searchView=findViewById(R.id.searchviewmainrec);
        toolbar=findViewById(R.id.toolbarmainrec);
        setSupportActionBar(toolbar);


        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        /*textView.setOnClickListener(v -> {
            alertdialogbuilder=new AlertDialog.Builder(ReceiverMainPage.this);
            alertdialogbuilder.setMessage("Do you want to be logged out and go to first page?");
            alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(ReceiverMainPage.this, MainActivity2.class));
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


        });*/

        FirebaseRecyclerOptions<ReceiverModel> options =
                new FirebaseRecyclerOptions.Builder<ReceiverModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donor"), ReceiverModel.class)
                        .build();

        adapter=new MyAdapter(options);

        recyclerView.setAdapter(adapter);




    }





    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
        /*if(searchView!=null)
        {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ArrayList<ReceiverModel> user = new ArrayList<>();
                    for(ReceiverModel object : array )
                    {
                        if(object.getbgroup().toLowerCase().contains(newText.toLowerCase())||object.getdistrict().toLowerCase().contains(newText.toLowerCase()))
                        {
                            user.add(object);
                        }
                    }

                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    return true;
                }
            });
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    public void processsearch(String s) {


        FirebaseRecyclerOptions<ReceiverModel> options =
                new FirebaseRecyclerOptions.Builder<ReceiverModel>()
                        //unicode end character "\uf8ff
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("donor").orderByChild("district").startAt(s.toLowerCase()).endAt(s+"\uf8ff"), ReceiverModel.class)
                        .build();



        if(options.getSnapshots().size()==0)
        {
             options =
                    new FirebaseRecyclerOptions.Builder<ReceiverModel>()
                            //unicode end character "\uf8ff
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("donor").orderByChild("bgroup").startAt(s.toLowerCase()).endAt(s+"\uf8ff"), ReceiverModel.class)
                            .build();
        }
        adapter= new MyAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);


    }




    //for back button alert message
    @Override
    public void onBackPressed() {
        alertdialogbuilder=new AlertDialog.Builder(ReceiverMainPage.this);
        alertdialogbuilder.setMessage("Do you want to be logged out and go to first page?");
        alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(ReceiverMainPage.this, MainActivity2.class));
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

    }  //back function end

    /*public class Demo{
        public void main(String[]args)
        {
            Calendar calendar= Calendar.getInstance();
            calendar.add(Calendar.MONTH,2);
        }
    }*/


}