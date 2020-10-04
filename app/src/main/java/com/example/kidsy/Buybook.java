package com.example.kidsy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

public class Buybook extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle dtoggle;
    private Button btn,cal;
    Context context;
    TextView booktotalprice;
    EditText bookname,bookprice,bookqty,bookship;
    private String key;

    long total =0;
    long ship;
    long price;
    long qty;
    String btotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buybook);

        drawerLayout = findViewById(R.id.buynowlay);
        dtoggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.hopen, R.string.hclose);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookname = findViewById(R.id.buybookname);
        bookprice = findViewById(R.id.buybookprice);
        bookship = findViewById(R.id.buybookship);
        bookqty = findViewById(R.id.buybookqty);
        booktotalprice = findViewById(R.id.buybooktotal);

        cal=findViewById(R.id.btncal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bqty = bookqty.getText().toString();
                qty = Long.parseLong(bqty);
                String bship = bookship.getText().toString();
                ship = Long.parseLong(bship);
                String bprice = bookprice.getText().toString();
                price = Long.parseLong(bprice);

                total = (qty*price)+(qty*ship);
                btotal = Long.toString(total);
                bqty = Long.toString(qty);
                bookqty.setText(bqty);
                booktotalprice.setText(btotal);

            }
        });

        bookname = findViewById(R.id.buybookname);
        bookprice = findViewById(R.id.buybookprice);
        bookship = findViewById(R.id.buybookship);
        bookqty = findViewById(R.id.buybookqty);
        booktotalprice = findViewById(R.id.buybooktotal);
        btn = findViewById(R.id.btnbuynow);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(btotal)){
                    booktotalprice.setError("Please Click the button to calculate your total payment");
                    return;
                }
                Intent intent=new Intent(getBaseContext(),Payment.class);
                intent.putExtra("key",key);
                intent.putExtra("paybook",bookname.getText().toString());
                intent.putExtra("paybookqty",bookqty.getText().toString());
                intent.putExtra("paybooktotal",booktotalprice.getText().toString());

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (dtoggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}