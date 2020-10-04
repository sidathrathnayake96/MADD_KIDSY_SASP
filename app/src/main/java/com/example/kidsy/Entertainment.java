package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Entertainment extends AppCompatActivity {

    Button btn4,btn6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entertainment);

        btn4=(Button)findViewById(R.id.quote_btn);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Entertainment.this,Quote.class);
                startActivity(intent);

                Toast toast = Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                toast.show();
            }

        });

        btn6=(Button)findViewById(R.id.vm_btn);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Entertainment.this,MotivationalVideo.class);
                startActivity(intent);

                Toast toast = Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                toast.show();
            }

        });

    }
}