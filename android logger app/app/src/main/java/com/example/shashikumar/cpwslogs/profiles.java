package com.example.shashikumar.cpwslogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class profiles extends AppCompatActivity {

    EditText username;
    EditText password;
    EditText password2;
    Button saveprofilebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        saveprofilebtn = findViewById(R.id.saveprofile);
        saveprofilebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveprofiledata();
            }

            public void saveprofiledata() {
               showsaved();
            }
        });
    }

    private void showsaved() {
        Toast.makeText(this, "profile saved", Toast.LENGTH_SHORT).show();

    }

}
