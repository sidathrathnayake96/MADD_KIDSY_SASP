package com.example.kidsy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StoreDetailsActivity extends AppCompatActivity {

    private ImageButton backBtn,filterBookBtn;
    private EditText searchBookEt;
    private TextView filterBooksTv;
    private RecyclerView booksRv;

    private FirebaseAuth firebaseAuth;

    private String bookId;
    private ArrayList<ModelBook>bookList;
    private AdapterProductUser adapterProductUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        backBtn=findViewById(R.id.backBtn);
        filterBookBtn=findViewById(R.id.filterBookBtn);
        searchBookEt=findViewById(R.id.searchBookEt);
        filterBooksTv=findViewById(R.id.filterBooksTv);
        booksRv=findViewById(R.id.booksRv);

       // bookId =getIntent().getStringExtra("bookId");
       // firebaseAuth=FirebaseAuth.getInstance();

        loadStoreProducts();


        searchBookEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{

                    adapterProductUser.getFilter().filter(s);
                }catch (Exception e){

                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        filterBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder =new AlertDialog.Builder(StoreDetailsActivity.this);
                builder.setTitle("Choose Category:")
                        .setItems(Constants.bookcategories1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String selected = Constants.bookcategories1[which];
                                filterBooksTv.setText(selected);
                                if(selected.equals("All")){
                                    loadStoreProducts();
                                }
                                else{
                                   adapterProductUser.getFilter().filter(selected);
                                }
                            }
                        })
                        .show();

            }
        });



    }

    private void loadStoreProducts() {

        bookList =new ArrayList<>();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Books");
        reference.child("book")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        bookList.clear();
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            ModelBook modelBook = ds.getValue(ModelBook.class);
                            bookList.add(modelBook);
                        }
                        adapterProductUser =new AdapterProductUser(StoreDetailsActivity.this,bookList);
                        booksRv.setAdapter(adapterProductUser);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}