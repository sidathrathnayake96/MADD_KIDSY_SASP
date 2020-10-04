/*
package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Button addad = findViewById(R.id.addadmin);
        addad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),addnewspaper.class);
                startActivity(intent);
            }
        });

        Button updatead = findViewById(R.id.updateadmin);
        updatead.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),updatenewspaper.class);
                startActivity(intent);
            }
        });

    }

}
*/