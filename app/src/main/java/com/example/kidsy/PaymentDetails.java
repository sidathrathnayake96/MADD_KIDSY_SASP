package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class PaymentDetails extends AppCompatActivity {

    private EditText epayid,epayfirstname,epayemail,epayaddress,epaycardnumber,epayccv,epaycardexpiredate,epaybookname,epayqty,epaytotalprice,epaydate;
    private Button ubtn,dbtn;
    private String key;
    private String payid;
    private String payfirstname;
    private String payemail;
    private String payaddress;
    private String paycardnumber;
    private String payccv;
    private String paycardexpiredate;
    private String paybookname;
    private String payqty;
    private String paytotalprice;
    private String paydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        key = getIntent().getStringExtra("key");
        payid = getIntent().getStringExtra("payid");
        payfirstname = getIntent().getStringExtra("payfirstname");payemail = getIntent().getStringExtra("payemail");
        payaddress = getIntent().getStringExtra("payaddress");
        paycardnumber = getIntent().getStringExtra("paycardnumber");
        payccv = getIntent().getStringExtra("payccv");
        paycardexpiredate = getIntent().getStringExtra("paycardexpiredate");
        paybookname = getIntent().getStringExtra("paybookname");
        payqty = getIntent().getStringExtra("payqty");
        paytotalprice = getIntent().getStringExtra("paytotalprice");
        paydate = getIntent().getStringExtra("paydate");

        epayid=(EditText) findViewById(R.id.payid);
        epayid.setText(payid);
        epayfirstname=(EditText) findViewById(R.id.payfirstname);
        epayfirstname.setText(payfirstname);
        epayemail=(EditText) findViewById(R.id.payemail);
        epayemail.setText(payemail);
        epayaddress=(EditText) findViewById(R.id.payaddress);
        epayaddress.setText(payaddress);
        epaycardnumber=(EditText) findViewById(R.id.paycardnumber);
        epaycardnumber.setText(paycardnumber);
        epayccv=(EditText) findViewById(R.id.payccv);
        epayccv.setText(payccv);
        epaycardexpiredate=(EditText) findViewById(R.id.paycardexpiredate);
        epaycardexpiredate.setText(paycardexpiredate);
        epaybookname=(EditText) findViewById(R.id.paybookname);
        epaybookname.setText(paybookname);
        epayqty=(EditText) findViewById(R.id.payqty);
        epayqty.setText(payqty);
        epaytotalprice=(EditText) findViewById(R.id.paytotalprice);
        epaytotalprice.setText(paytotalprice);
        epaydate=(EditText) findViewById(R.id.paydate);
        epaydate.setText(paydate);

        ubtn = (Button) findViewById(R.id.btnupdatepayment);
        dbtn = (Button) findViewById(R.id.btndltpayment);

        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PaymentData paymentdatas= new PaymentData();

                paymentdatas.setPayid(epayid.getText().toString());
                paymentdatas.setPayfirstname(epayfirstname.getText().toString());
                paymentdatas.setPayemail(epayemail.getText().toString());
                paymentdatas.setPayaddress(epayaddress.getText().toString());
                paymentdatas.setPaycardnumber(epaycardnumber.getText().toString());
                paymentdatas.setPayccv(epayccv.getText().toString());
                paymentdatas.setPaycardexpiredate(epaycardexpiredate.getText().toString());
                paymentdatas.setPaybookname(epaybookname.getText().toString());
                paymentdatas.setPayqty(epayqty.getText().toString());
                paymentdatas.setPaytotalprice(epaytotalprice.getText().toString());
                paymentdatas.setPaydate(epaydate.getText().toString());


                new ReadPayment().updatePaymentData(key, paymentdatas, new ReadPayment.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PaymentData> payment, List<String> keys) {

                    }

                    @Override
                    public void Inserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                        Toast.makeText(PaymentDetails.this,"Payment Details has been updated successfully",Toast.LENGTH_LONG).show();

                       Intent intent= new Intent(PaymentDetails.this,PaymentListActivity.class);
                       startActivity(intent);
                    }

                    @Override
                    public void DataIsDeleted() {

                    }
                });
            }
        });

        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ReadPayment().deletePaymentData(key, new ReadPayment.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<PaymentData> paymentData, List<String> keys) {

                    }

                    @Override
                    public void Inserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(PaymentDetails.this,"Payment Details has been deleted successfully",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(PaymentDetails.this,PaymentListActivity.class);
                        startActivity(intent);
                        finish(); return;
                    }
                });
            }
        });
    }




}