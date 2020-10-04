/*package com.example.kidsy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class readnow extends AppCompatActivity {

    TextView a,b;
    DatabaseReference reff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readnow);

//        a = (TextView) findViewById(R.id.texthead);
//        b = (TextView) findViewById(R.id.textView4);


//        reff = FirebaseDatabase.getInstance().getReference().child("DeisesID").child("1");
//        reff.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String Name = dataSnapshot.child("name").getValue().toString();
//                String Cause_it = dataSnapshot.child("cause_it").getValue().toString();

//                a.setText(Name);
//                b.setText(Cause_it);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

 */