package com.example.khalid_ali_180145;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class List extends AppCompatActivity {
    ArrayList<String> al = new ArrayList<>();
    ListView myLV;
    FirebaseDatabase db;
    DatabaseReference reference;
    DatabaseReference mRef;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    DatabaseHelper mydb;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

            user = new Users();
            myLV = (ListView) findViewById(R.id.listV);
            db = FirebaseDatabase.getInstance();
            reference = db.getReference("Users ");
            list = new ArrayList<>();

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot ds: snapshot.getChildren()){

                        user = ds.getValue(Users.class);
                        list.add("First name: "+user.getname() + "\nEmail: "+user.getemail() + "\nID: "+user.getid());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }
