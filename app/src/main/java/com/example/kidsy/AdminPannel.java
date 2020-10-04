package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.paperdb.Paper;

public class AdminPannel extends AppCompatActivity {

    private Button LogoutBtn, QuoteBtn, AddEmp, btnvideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);

        LogoutBtn=findViewById(R.id.logout_btn);
        QuoteBtn=findViewById(R.id.qadd);
        AddEmp=findViewById(R.id.add_emp);


        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Paper.book().destroy();

                Intent intent = new Intent(AdminPannel.this,Welcome.class);
                startActivity(intent);
            }
        });

        QuoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPannel.this,AdminQuote.class);
                startActivity(intent);
            }
        });


        AddEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPannel.this,AdminEmployee.class);
                startActivity(intent);
            }
        });

        btnvideo=(Button)findViewById(R.id.vid);
        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(AdminPannel.this,AdminVideo.class);
                startActivity(intent);

                Toast toast = Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                toast.show();
            }

        });


    }
}