package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class Home extends AppCompatActivity {

    private Button LogoutBtn, Ent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LogoutBtn=findViewById(R.id.logout_btn);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Paper.book().destroy();

                Intent intent = new Intent(Home.this,Welcome.class);
                startActivity(intent);
            }
        });

        Ent=findViewById(R.id.btn_Ent);
        Ent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Entertainment.class);
                startActivity(intent);

            }
        });

    }
}