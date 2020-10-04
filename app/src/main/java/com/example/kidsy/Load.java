package com.example.kidsy;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Load extends AppCompatActivity {
    private static int SPLASH=3000;
    Animation topAnim,botAnim;
    ImageView img;
    TextView logo,welcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        botAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        img=(ImageView) findViewById(R.id.logoImage);
        logo=(TextView) findViewById(R.id.logoText);
        welcome=(TextView) findViewById(R.id.logowelcome);

        img.setAnimation(topAnim);
        logo.setAnimation(botAnim);
        welcome.setAnimation(botAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Load.this,Welcome.class);
                startActivity(intent);
                finish();
            }
        },SPLASH);

    }
}