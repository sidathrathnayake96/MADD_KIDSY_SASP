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

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle dtoggle;

    private Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.homelayout1);
        dtoggle = new ActionBarDrawerToggle(this,drawerLayout, R.string.hopen, R.string.hclose);
        dtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        btn = findViewById(R.id.homebtnbooks);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBuybook();
            }
        });

     /*   btn = findViewById(R.id.homebtnnewspapers);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewspapers();
            }
        });


      */
        btn = findViewById(R.id.homebtnentertainment);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEntertainment();
            }
        });

    }
    public void openBuybook(){
        Intent intent = new Intent(this,StoreDetailsActivity.class);
        startActivity(intent);
    }
 /*   public void openNewspapers(){
        Intent intent = new Intent(this,newspapercategory.class);
        startActivity(intent);
    }
*/
    public void openEntertainment(){
        Intent intent = new Intent(this,Home.class);
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



