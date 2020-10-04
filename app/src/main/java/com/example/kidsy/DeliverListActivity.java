package com.example.kidsy;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DeliverListActivity extends AppCompatActivity {

    private RecyclerView drecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_deliver_list);


        drecyclerView = (RecyclerView) findViewById(R.id.readdeliverlay);
        new ReadDelivery().readDeliver(new ReadDelivery.DataStatus() {
            @Override
            public void DataIsLoaded(List<DeliverData> deliverData, List<String> keys) {
                new DeliverRecyclerView().setConfig(drecyclerView, DeliverListActivity.this,deliverData,keys);
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