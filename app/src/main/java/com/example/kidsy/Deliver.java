package com.example.kidsy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Deliver extends AppCompatActivity {


    private DeliverData deliverData;
    private FirebaseDatabase database;
    private DatabaseReference payref;
    private Button btn;
    long delid = 0;
    private String key;
    private String payfirstname;
    private String payemail;
    private String payaddress;
    private String paybookname;
    private String payqty;

    TextView ddelfirst,ddelemail,ddeladdress,ddelbook,ddelqty;
    EditText ddeldate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver);

        Button bhbtn = findViewById(R.id.backhome);
        bhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Deliver.this,AdminHome.class);
                startActivity(intent);
            }
        });

        key = getIntent().getStringExtra("key");
        payfirstname = getIntent().getStringExtra("payfirstname");
        payemail = getIntent().getStringExtra("payemail");
        payaddress = getIntent().getStringExtra("payaddress");
        paybookname = getIntent().getStringExtra("paybookname");
        payqty = getIntent().getStringExtra("payqty");


        ddelfirst = (TextView) findViewById(R.id.delfirst);
        ddelfirst.setText(payfirstname);
        ddelemail = (TextView) findViewById(R.id.delemail);
        ddelemail.setText(payemail);
        ddeladdress = (TextView) findViewById(R.id.deladdress);
        ddeladdress.setText(payaddress);
        ddelbook = (TextView) findViewById(R.id.delbook);
        ddelbook.setText(paybookname);
        ddelqty = (TextView) findViewById(R.id.delqty);
        ddelqty.setText(payqty);



        ddelfirst=findViewById(R.id.delfirst);
        ddelemail=findViewById(R.id.delemail);
        ddeladdress=findViewById(R.id.deladdress);
        ddelbook=findViewById(R.id.delbook);
        ddelqty=findViewById(R.id.delqty);
        ddeldate=findViewById(R.id.deldate);
        btn = findViewById(R.id.btndeliver);
        deliverData = new DeliverData();

        payref = database.getInstance().getReference().child("DeliverData");
      payref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    delid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String delfirst = ddelfirst.getText().toString().trim();
                String delemail = ddelemail.getText().toString().trim();
                String deladdress = ddeladdress.getText().toString().trim();
                String delbook = ddelbook.getText().toString().trim();
                String delqty = ddelqty.getText().toString().trim();
                String deldate = ddeldate.getText().toString().trim();

                if (TextUtils.isEmpty(delfirst)) {
                    ddelfirst.setError("First Name can not be empty");
                    return;
                }
                if (TextUtils.isEmpty(delemail)) {
                    ddelemail.setError("Email can not be empty");
                    return;
                }

                if (TextUtils.isEmpty(deladdress)) {
                    ddeladdress.setError("Please Enter the Postal Address");
                    return;
                }
                if (TextUtils.isEmpty(delbook)) {
                    ddelbook.setError("Please enter book name");
                    return;
                }
                if (TextUtils.isEmpty(deldate)) {
                    ddeldate.setError("Please enter order delivered date");
                    return;
                }

                deliverData.setDelfirst(delfirst);
                deliverData.setDelemail(delemail);
                deliverData.setDeladdress(deladdress);
                deliverData.setDelbook(delbook);
                deliverData.setDelqty(delqty);
                deliverData.setDeldate(deldate);


                payref.child(String.valueOf(delid+1)).setValue(deliverData);
                Toast.makeText(getApplicationContext(), "Order Delivered Successfully", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getBaseContext(),PaymentEmail.class);
                intent.putExtra("key",key);
                intent.putExtra("payemail",ddelemail.getText().toString());
                startActivity(intent);

            }
        });
    }

}