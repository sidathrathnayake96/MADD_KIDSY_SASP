package com.example.kidsy;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainUserActivity extends AppCompatActivity {

    private ImageButton logouttBtn,addProductBtn,filterBookBtn;
    TextView tabProductsTv,tabOrdersTv,filterBooksTv;
    RelativeLayout productRl,orderRl;
    EditText searchBookEt;
    RecyclerView booksRv;

    private ArrayList<ModelBook> productList;
    private AdapterProductSeller adapterProductSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);




        logouttBtn=findViewById(R.id.logouttBtn);
        tabProductsTv=findViewById(R.id.tabProductsTv);
        productRl=findViewById(R.id.productRl);
        searchBookEt=findViewById(R.id.searchBookEt);
        filterBookBtn=findViewById(R.id.filterBookBtn);
        filterBooksTv=findViewById(R.id.filterBooksTv);
        orderRl=findViewById(R.id.orderRl);
        tabOrdersTv=findViewById(R.id.tabOrdersTv);
        booksRv=findViewById(R.id.booksRv);


        loadAllProducts();
        showProductUI();

        searchBookEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{

                    adapterProductSeller.getFilter().filter(s);
                }catch (Exception e){

                    e.printStackTrace();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        logouttBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logout
            }
        });


        tabProductsTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProductUI();


            }
        });

        tabOrdersTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showOrderUI();

            }
        });

        filterBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =new AlertDialog.Builder(MainUserActivity.this);
                builder.setTitle("Choose Category:")
                        .setItems(Constants.bookcategories1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String selected = Constants.bookcategories1[which];
                                filterBooksTv.setText(selected);
                                if(selected.equals("All")){
                                    loadAllProducts();
                                }
                                else{
                                    loadFilteredProducts(selected);
                                }
                            }
                        })
                        .show();
            }
        });


    }

    private void loadFilteredProducts(final String selected) {

        productList=new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child("book")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        productList.clear();
                        for(DataSnapshot ds:dataSnapshot.getChildren()){

                            String bookCategory = ""+ds.child("bookCategory").getValue();
                            if(selected.equals(bookCategory)){
                                ModelBook modelBook =ds.getValue(ModelBook.class);
                            }

                            ModelBook modelBook=ds.getValue(ModelBook.class);
                            productList.add(modelBook);
                        }
                        adapterProductSeller =new AdapterProductSeller(MainUserActivity.this,productList);
                        booksRv.setAdapter(adapterProductSeller);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }

    private void loadAllProducts() {

        productList=new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Books");
        reference.child("book")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        productList.clear();
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            ModelBook modelBook=ds.getValue(ModelBook.class);
                            productList.add(modelBook);
                        }
                        adapterProductSeller =new AdapterProductSeller(MainUserActivity.this,productList);
                        booksRv.setAdapter(adapterProductSeller);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
    }



    private void showProductUI() {
        productRl.setVisibility(View.VISIBLE);
        orderRl.setVisibility(View.GONE);

        tabProductsTv.setTextColor(getResources().getColor(R.color.colorAccent));
        tabProductsTv.setBackgroundResource(R.drawable.shape_rec02);

        tabOrdersTv.setTextColor(getResources().getColor(R.color.colorWhite));
        tabOrdersTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));

    }
    private void showOrderUI() {

        orderRl.setVisibility(View.VISIBLE);
        productRl.setVisibility(View.GONE);


        tabProductsTv.setTextColor(getResources().getColor(R.color.colorWhite));
        tabProductsTv.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        tabOrdersTv.setTextColor(getResources().getColor(R.color.colorAccent));
        tabOrdersTv.setBackgroundResource(R.drawable.shape_rec02);

    }



}