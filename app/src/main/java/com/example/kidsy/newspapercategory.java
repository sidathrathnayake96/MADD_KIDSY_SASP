/*
package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class newspapercategory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspapercategory);


        Button grade12_13 = findViewById(R.id.gradebtn1213);
        grade12_13.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),grade12to13.class);
                startActivity(intent);
            }
        });

        Button grade6_11 = findViewById(R.id.gradebtn611);
        grade6_11.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), grade6to11.class);
                startActivity(intent);
            }
        });

        Button grade1_5 = findViewById(R.id.gradebtn15);
        grade1_5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),grade1to5.class);
                startActivity(intent);
            }
        });

        Button articles = findViewById(R.id.articlesbtn);
        articles.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),articles.class);
                startActivity(intent);
            }
        });

        Button admin = findViewById(R.id.admin);
         admin.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
                 Intent intent = new Intent(getApplicationContext(), admin.class);
                 startActivity(intent);
             }
         });
    }
}

 */