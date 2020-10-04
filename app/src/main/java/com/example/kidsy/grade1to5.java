/*
ackage com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class grade1to5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade1to5);

        Button readnow = findViewById(R.id.newspaperreadnow1);
        readnow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),readnow.class);
                startActivity(intent);
            }
        });

        Button readnow1 = findViewById(R.id.newspaperreadnow);
        readnow1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),readnow.class);
                startActivity(intent);
            }
        });

    }
}
*/