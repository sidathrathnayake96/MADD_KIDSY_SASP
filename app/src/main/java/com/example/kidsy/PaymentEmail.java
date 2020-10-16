package com.example.kidsy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PaymentEmail extends AppCompatActivity {
    Button btn,sbtn;
    private String key;
    private String payemail;
    TextView ddelemail,ddelsubject;
    EditText ddelmsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_email);

        key = getIntent().getStringExtra("key");
        payemail = getIntent().getStringExtra("payemail");

        ddelemail = (TextView) findViewById(R.id.emailemail);
        ddelemail.setText(payemail);

        ddelemail=findViewById(R.id.emailemail);
        ddelsubject=findViewById(R.id.emailsubject);
        ddelmsg = findViewById(R.id.emailmsg);
        btn = findViewById(R.id.btnemail);
        sbtn = findViewById(R.id.btnemaildone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PaymentEmail.this,Admindeliver.class);
                startActivity(intent);
            }
        });


    }
    public void sendEmail(){
            String resList = ddelemail.getText().toString();
            String[] res = resList.split(",");
            String subject = ddelsubject.getText().toString();
            String message=ddelmsg.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,res);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an Email client"));
    }
}