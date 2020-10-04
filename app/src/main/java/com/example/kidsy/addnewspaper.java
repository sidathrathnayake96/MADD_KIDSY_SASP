/*
package com.example.kidsy;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addnewspaper extends AppCompatActivity {

    EditText newspapername1,author;
    Button btnAdd;
    DatabaseReference dbRef;
    newspaper news;

    long maxid=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewspaper);

        newspapername1 = findViewById(R.id.newspapername1);
        author = findViewById(R.id.author);

        btnAdd = findViewById(R.id.add);
        news = new newspaper();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("newspaper");
                dbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists())
                            maxid = (dataSnapshot.getChildrenCount());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                try {
                    if (TextUtils.isEmpty(newspapername1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty news paper name",
                                Toast.LENGTH_SHORT).show();

                    else if (TextUtils.isEmpty(author.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty author",
                                Toast.LENGTH_SHORT).show();

                    else {
                        news.setNewspapername1(newspapername1.getText().toString().trim());
                        news.setAuthor(author.getText().toString().trim());
                        dbRef.child(String.valueOf(maxid+1)).setValue(news);

                        Toast.makeText(addnewspaper.this, R.string.datainsert, Toast.LENGTH_LONG).show();
                    }
                } finally {

                }}
            });
        }
            }
*/