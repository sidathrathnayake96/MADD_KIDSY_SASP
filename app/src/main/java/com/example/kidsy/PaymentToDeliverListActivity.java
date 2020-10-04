package com.example.kidsy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PaymentToDeliverListActivity extends AppCompatActivity {
    private RecyclerView drecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_paymenttodeliver_list);


        drecyclerView = (RecyclerView) findViewById(R.id.readpaydellay);
        new ReadPaymentToDeliver().readPaymentToDeliver(new ReadPaymentToDeliver.DataStatus() {
            @Override
            public void DataIsLoaded(List<PaymentData> paymentData, List<String> keys) {
                new PaymentToDeliverRecyclerView().setConfig(drecyclerView, PaymentToDeliverListActivity.this,paymentData,keys);
            }

            @Override
            public void Inserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
    }

}
