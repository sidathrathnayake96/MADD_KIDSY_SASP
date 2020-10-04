package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DeliverDetails extends AppCompatActivity {

    private EditText dorderid,ddelfirst,ddelemail,ddeladdress,ddelbook,ddelqty,ddeldate;
    private Button ubtn,dbtn;
    private String key;
    private String orderid;
    private String delfirst;
    private String delemail;
    private String deladdress;
    private String delbook;
    private String delqty;
    private String deldate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_details);

        key = getIntent().getStringExtra("key");
        orderid = getIntent().getStringExtra("orderid");
        delfirst = getIntent().getStringExtra("delfirst");
        delemail = getIntent().getStringExtra("delemail");
        deladdress = getIntent().getStringExtra("deladdress");
        delbook = getIntent().getStringExtra("delbook");
        delqty = getIntent().getStringExtra("delqty");
        deldate = getIntent().getStringExtra("deldate");

        dorderid=(EditText) findViewById(R.id.orderid);
        dorderid.setText(orderid);
        ddelfirst=(EditText) findViewById(R.id.delfirst);
        ddelfirst.setText(delfirst);
        ddelemail=(EditText) findViewById(R.id.delemail);
        ddelemail.setText(delemail);
        ddeladdress=(EditText) findViewById(R.id.deladdress);
        ddeladdress.setText(deladdress);
        ddelbook=(EditText) findViewById(R.id.delbook);
        ddelbook.setText(delbook);
        ddelqty=(EditText) findViewById(R.id.delqty);
        ddelqty.setText(delqty);
        ddeldate=(EditText) findViewById(R.id.deldate);
        ddeldate.setText(deldate);

        ubtn = findViewById(R.id.btnupdatedeliver);
        dbtn = findViewById(R.id.btndltdeliver);

        ubtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeliverData deliverData= new DeliverData();
                        deliverData.setOrderid(dorderid.getText().toString());
                        deliverData.setDelfirst(ddelfirst.getText().toString());
                        deliverData.setDelemail(ddelemail.getText().toString());
                        deliverData.setDeladdress(ddeladdress.getText().toString());
                        deliverData.setDelbook(ddelbook.getText().toString());
                        deliverData.setDelqty(ddelqty.getText().toString());
                        deliverData.setDeldate(ddeldate.getText().toString());

                        new ReadDelivery().updateDeliverData(key, deliverData, new ReadDelivery.DataStatus() {
                            @Override
                            public void DataIsLoaded(List<DeliverData> deliverData, List<String> keys) {

                            }

                            @Override
                            public void Inserted() {

                            }

                            @Override
                            public void DataIsUpdated() {
                                Toast.makeText(DeliverDetails.this,"Deliver Details has been updated successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(DeliverDetails.this, DeliverListActivity.class);
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
                new ReadDelivery().deleteDeliverData(key, new ReadDelivery.DataStatus() {
                    @Override
                    public void DataIsLoaded(List<DeliverData> deliverData, List<String> keys) {

                    }

                    @Override
                    public void Inserted() {

                    }

                    @Override
                    public void DataIsUpdated() {

                    }

                    @Override
                    public void DataIsDeleted() {
                        Toast.makeText(DeliverDetails.this,"Deliver Details has been deleted successfully",Toast.LENGTH_LONG).show();
                        Intent intent= new Intent(DeliverDetails.this, Admindeliver.class);
                        startActivity(intent);
                        finish(); return;
                    }
                });
            }
        });
    }




}