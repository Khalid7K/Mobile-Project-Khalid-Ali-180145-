package com.example.khalid_ali_180145;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class MainActivity3 extends AppCompatActivity {
    EditText UN;
    EditText Id;
    EditText Email;
    Button DisplayFB;
    Button addfirebase;
    Button deletebyemail;
    String name;
    String id;
    String email;
    FirebaseDatabase RN;
    DatabaseReference r;



    LinearLayout updatingLayout;
    int x=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        EditText UN = (EditText)findViewById(R.id.UserName);
        EditText Id = (EditText)findViewById(R.id.IDNumber);
        EditText Email = (EditText)findViewById(R.id.UserEmail);
        Button DisplayFB = (Button)findViewById(R.id.DisplayFB);
        Button addfirebase = (Button)findViewById(R.id.AddFB);
        Button deletebyemail = (Button)findViewById(R.id.DeleteEm);
        Button Mainmenu = (Button)findViewById(R.id.MainMenu);

        EditText PrevEmail = (EditText)findViewById(R.id.OldEmail);
        EditText NewEmail = (EditText)findViewById(R.id.NewEmail);
        Button updateData = (Button)findViewById(R.id.UpdateD);


        DisplayFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, List.class));
            }
        });

        addfirebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RN = FirebaseDatabase.getInstance();
                r = RN.getReference();
                name = UN.getText().toString();
                email = Email.getText().toString();
                id = Id.getText().toString();
                if (TextUtils.isEmpty(name)|| TextUtils.isEmpty(email) || TextUtils.isEmpty(id)) {
                    Toast.makeText(MainActivity3.this, "please enter all information", Toast.LENGTH_LONG).show();

                } else {
                    UN.getText().clear();
                    Email.getText().clear();
                    Id.getText().clear();
                    Toast.makeText(MainActivity3.this, "User Added to the Firebase Database Successfully", Toast.LENGTH_LONG).show();
                }
            }
        });

        deletebyemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email= Email.getText().toString();
                if (TextUtils.isEmpty(Email.getText().toString())) {

                    Toast.makeText(MainActivity3.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                } else {
                    deleteU(email);
                }

            }
        });

        Mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
            }
        });




    }

    private void deleteU(String email) {
    }
}