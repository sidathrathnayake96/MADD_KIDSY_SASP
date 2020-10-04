package com.example.kidsy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.kidsy.Model.Users;
import com.example.kidsy.Prevalent.Prevalent;

import io.paperdb.Paper;

public class Welcome extends AppCompatActivity {

    private Button joinNowButton,loginButton;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        joinNowButton=(Button) findViewById(R.id.main_join_now_btn);
        loginButton=(Button) findViewById(R.id.main_login_btn);
        loadingBar= new ProgressDialog(this);

        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Login.class);
                startActivity(intent);
            }
        });

        joinNowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Register.class);
                startActivity(intent);
            }
        });

        //remember me
        String UserPhoneKey=Paper.book().read(Prevalent.UserPhoneKey);
        String UserPasswordKey=Paper.book().read(Prevalent.UserPasswordKey);

        if(UserPhoneKey != "" && UserPasswordKey != ""){
            if(!TextUtils.isEmpty(UserPhoneKey) && !TextUtils.isEmpty(UserPasswordKey)){
                AllowAccess(UserPhoneKey,UserPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }
    }

    //login validating
    private void AllowAccess(final String phone, final String password) {

        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("Users").child(phone).exists()){
                    Users usersData=dataSnapshot.child("Users").child(phone).getValue(Users.class);

                    if(usersData.getPhone().equals(phone)){

                        if(usersData.getPassword().equals(password)){
                            Toast.makeText(Welcome.this,"Login Successful",Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(Welcome.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            loadingBar.dismiss();
                            Toast.makeText(Welcome.this,"Password is incorrect",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    Toast.makeText(Welcome.this,"Account with this " + phone + " number do not exists...",Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(Welcome.this,"You need to create a new account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}