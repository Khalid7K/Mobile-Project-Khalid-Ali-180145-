package com.example.khalid_ali_180145;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText username;
    EditText id;
    EditText email;
    Button display;
    Button Insert;
    Button Update;
    Button delete;
    Button MainMenu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EditText username = (EditText)findViewById(R.id.Name);
        EditText id = (EditText)findViewById(R.id.ID);
        EditText email = (EditText)findViewById(R.id.EmailAddress);
        Button display = (Button)findViewById(R.id.Display);
        Button Insert = (Button)findViewById(R.id.insert);
        Button Update = (Button)findViewById(R.id.update);
        Button delete = (Button)findViewById(R.id.Delete);
        Button MainMenu = (Button)findViewById(R.id.back);

        myDB = new DatabaseHelper(this);

        display.setOnClickListener(new View.OnClickListener() {
            Cursor cur;
            StringBuffer buffer=new StringBuffer();

                @Override
                public void onClick(View v) {
                    if (username.getText().toString().equals("")) {
                        cur = myDB.getListContents();
                    } else if (!(username.getText().toString().equals(""))) {
                        cur = myDB.getSpecificResult(username.getText().toString());
                    }

                    if (cur.getCount() == 0)
                        Toast.makeText(MainActivity2.this, "No results found !", Toast.LENGTH_LONG).show();
                    else {

                        while (cur.moveToNext()) {
                            for (int i = 0; i < cur.getColumnCount(); i++) {
                                buffer.append(cur.getColumnName(i) + ": " + cur.getString(i) + "\n");
                            }
                            buffer.append("\n");

                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                        builder.setCancelable(true);
                        builder.setTitle("Results");
                        builder.setMessage(buffer.toString());
                        builder.show();

                        buffer.delete(0, buffer.capacity());

                    }
                }
        });

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDB.addData(username.getText().toString(), id.getText().toString(), email.getText().toString())==false) {
                    Toast.makeText(MainActivity2.this, "Data was not entered...Please check your input!", Toast.LENGTH_LONG).show();
                }

                else if(myDB.addData(username.getText().toString(), id.getText().toString(), email.getText().toString())==true) {
                    Toast.makeText(MainActivity2.this, "Data was successfully entered...", Toast.LENGTH_LONG).show();
                }
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(id.getText().toString()) || TextUtils.isEmpty(email.getText().toString()) ) {
                    Toast.makeText(MainActivity2.this, "Please enter ID and updated email", Toast.LENGTH_LONG).show();
                }
                myDB.update(Integer.parseInt(id.getText().toString()), email.getText().toString());
                Toast.makeText(MainActivity2.this, "The user: "+id.getText().toString()+" email have been updated", Toast.LENGTH_SHORT).show();
                id.getText().clear();
                email.getText().clear();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result=myDB.dltRow(username.getText().toString());

                if(result>=1) {
                    Toast.makeText(MainActivity2.this, +result + "Row(s) were susscessfully deleted", Toast.LENGTH_LONG).show();
                    username.getText().clear();
                    id.getText().clear();
                    email.getText().clear();
                }
                else {
                    Toast.makeText(MainActivity2.this, "No Data were deleted...Please try again", Toast.LENGTH_LONG).show();
                }
            }
        });

        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
            }
        });






    }
}