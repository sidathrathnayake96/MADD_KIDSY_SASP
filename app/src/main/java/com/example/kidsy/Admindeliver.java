package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Admindeliver extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle dtoggle;
    private Button dbtn,rbtn;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindeliver);

        drawerLayout = findViewById(R.id.admindeliverlay);
        dtoggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.hopen, R.string.hclose);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbtn = findViewById(R.id.homebtndeliver);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeliver();
            }
        });

        rbtn = findViewById(R.id.homebtnread);
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRead();
            }
        });

        Button bhbtn = findViewById(R.id.backhome);
        bhbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Admindeliver.this,AdminHome.class);
                startActivity(intent);
            }
        });
   }
    public void openDeliver(){
        Intent intent = new Intent(this,PaymentToDeliverListActivity.class);
        startActivity(intent);
    }

  public void openRead(){
        Intent intent = new Intent(this, DeliverListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(dtoggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
