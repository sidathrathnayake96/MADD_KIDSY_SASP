package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment extends AppCompatActivity {

    private PaymentData paymentData;
    private FirebaseDatabase database;
    private DatabaseReference payref;
    private Button btn;
    long pid = 0;

    private String key;
    private String paybook;
    private String paybookqty;
    private String paybooktotal;

    EditText epayfirstname,epayemail,epayaddress,epaycardnumber,epayccv,epaycardexpiredate,epaydate;
    TextView epaybookname,epayqty,epaytotalprice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        key = getIntent().getStringExtra("key");
        paybook = getIntent().getStringExtra("paybook");
        paybookqty = getIntent().getStringExtra("paybookqty");
        paybooktotal = getIntent().getStringExtra("paybooktotal");

        epayfirstname = findViewById(R.id.payfirstname);
        epayemail = findViewById(R.id.payemail);
        epayaddress = findViewById(R.id.payaddress);
        epaycardnumber = findViewById(R.id.paycardnumber);
        epayccv = findViewById(R.id.payccv);
        epaycardexpiredate = findViewById(R.id.paycardexpiredate);
        epaybookname = findViewById(R.id.paybookname);
        epaybookname.setText(paybook);
        epayqty = findViewById(R.id.payqty);
        epayqty.setText(paybookqty);
        epaytotalprice = findViewById(R.id.paytotalprice);
        epaytotalprice .setText(paybooktotal);
        epaydate = findViewById(R.id.paydate);

        epayfirstname = findViewById(R.id.payfirstname);
        epayemail = findViewById(R.id.payemail);
        epayaddress = findViewById(R.id.payaddress);
        epaycardnumber = findViewById(R.id.paycardnumber);
        epayccv = findViewById(R.id.payccv);
        epaycardexpiredate = findViewById(R.id.paycardexpiredate);
        epaybookname = findViewById(R.id.paybookname);
        epayqty = findViewById(R.id.payqty);
        epaytotalprice = findViewById(R.id.paytotalprice);
        epaydate = findViewById(R.id.paydate);
        paymentData = new PaymentData();

        Button bhbtn = findViewById(R.id.backhome);
        bhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Payment.this,MainUserActivity.class);
                startActivity(intent);
            }
        });

        btn = findViewById(R.id.btnpaycomplete);

        payref = database.getInstance().getReference().child("PaymentData");
        payref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    pid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String payfirstname = epayfirstname.getText().toString().trim();
                String payemail = epayemail.getText().toString().trim();
                String payaddress = epayaddress.getText().toString().trim();
                String paycardnumber = epaycardnumber.getText().toString().trim();
                String payccv = epayccv.getText().toString().trim();
                String paycardexpiredate = epaycardexpiredate.getText().toString().trim();
                String paybookname = epaybookname.getText().toString().trim();
                String payqty = epayqty.getText().toString().trim();
                String paytotalprice = epaytotalprice.getText().toString().trim();
                String paydate = epaydate.getText().toString().trim();


                if(TextUtils.isEmpty(payfirstname)){
                    epayfirstname.setError("First Name can not be empty");
                    return;
                }

                if(TextUtils.isEmpty(payemail)){
                    epayemail.setError("Please enter the Email");
                    return;
                }

                if(TextUtils.isEmpty(payaddress)){
                    epayaddress.setError("Address Field can not be empty");
                    return;
                }

                if(TextUtils.isEmpty(paycardnumber)){
                    epaycardnumber.setError("You must enter the Credit Card Number");
                    return;
                }
                if(paycardnumber.length()!=16)
                {
                    epaycardnumber.requestFocus();
                    epaycardnumber.setError("Invalid Card Number");
                    return;
                }
                if(TextUtils.isEmpty(payccv)){
                    epayccv.setError("Please enter the CCV");
                    return;
                }
                if(payccv.length()!=3)
                {
                    epayccv.requestFocus();
                    epayccv.setError("Invalid CCV");
                    return;
                }
                if(TextUtils.isEmpty(paycardexpiredate)){
                    epaycardexpiredate.setError("Please enter Credit card expire date");
                    return;
                }
                if(TextUtils.isEmpty(paybookname)){
                    epaybookname.setError("Please enter the book name");
                    return;
                }
                if(TextUtils.isEmpty(payqty)){
                    epayqty.setError("Please enter number of books");
                    return;
                }
                if(TextUtils.isEmpty(paytotalprice)){
                    epaytotalprice.setError("Please enter total payment correctly");
                    return;
                }
                if(TextUtils.isEmpty(paydate)){
                    epaydate.setError("Select the Payment date");
                    return;
                }


                paymentData.setPayfirstname(payfirstname);
                paymentData.setPayemail(payemail);
                paymentData.setPayaddress(payaddress);
                paymentData.setPaycardnumber(paycardnumber);
                paymentData.setPayccv(payccv);
                paymentData.setPaycardexpiredate(paycardexpiredate);
                paymentData.setPaybookname(paybookname);
                paymentData.setPayqty(payqty);
                paymentData.setPaytotalprice(paytotalprice);
                paymentData.setPaydate(paydate);

                payref.child(String.valueOf(pid+1)).setValue(paymentData);
                Toast.makeText(getApplicationContext(), "Payment added Successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Payment.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }




}