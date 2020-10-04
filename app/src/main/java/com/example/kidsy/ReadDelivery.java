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

public class ReadDelivery {

    private FirebaseDatabase database;
    private DatabaseReference dataref;
    private List<DeliverData> deliverData = new ArrayList<>();
    public interface DataStatus{
        void DataIsLoaded(List<DeliverData> deliverData, List<String> keys);
        void Inserted();
        void DataIsUpdated();
        void DataIsDeleted();


    }

    public ReadDelivery() {
        database = FirebaseDatabase.getInstance();
        dataref = database.getReference("DeliverData");
    }

    public void readDeliver(final DataStatus dataStatus){
        dataref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                deliverData.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : snapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    DeliverData deliverdata = keyNode.getValue(DeliverData.class);
                    deliverData.add(deliverdata);
                }
                dataStatus.DataIsLoaded(deliverData,keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void updateDeliverData(String key,DeliverData deliverdata,final DataStatus dataStatus){
        dataref.child(key).setValue(deliverdata).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsUpdated();
            }
        });
    }
    public void deleteDeliverData(String key,final DataStatus dataStatus){
        dataref.child(key).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dataStatus.DataIsDeleted();
            }
        });
    }

}
