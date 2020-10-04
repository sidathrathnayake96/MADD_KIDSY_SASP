package com.example.kidsy;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadPayment {

    private FirebaseDatabase database;
    private DatabaseReference dataref;
    private List<PaymentData> paymentData = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<PaymentData> paymentData, List<String> keys);
        void Inserted();
        void DataIsUpdated();
        void DataIsDeleted();


    }

    public ReadPayment() {
        database = FirebaseDatabase.getInstance();
        dataref = database.getReference("PaymentData");
    }

    public void readPayment(final ReadPayment.DataStatus dataStatus){
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                paymentData.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    PaymentData paymentdata = keyNode.getValue(PaymentData.class);
                    paymentData.add(paymentdata);
                }
                dataStatus.DataIsLoaded(paymentData,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updatePaymentData(String key, PaymentData payment, final ReadPayment.DataStatus dataStatus){
        dataref.child(key).setValue(payment).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deletePaymentData(String key,final DataStatus dataStatus){
        dataref.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

}

