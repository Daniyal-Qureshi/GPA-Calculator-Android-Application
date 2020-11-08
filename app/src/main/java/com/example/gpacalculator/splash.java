package com.example.gpacalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class splash extends AppCompatActivity {

    ImageView b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Animation animation=AnimationUtils.loadAnimation(splash.this,R.anim.fadein);
        b=(ImageView) findViewById(R.id.gpaimg);
        b.startAnimation(animation);

        try {

            new CountDownTimer(3000, 1000) {
                public void onTick(long Mil) {

                }

                public void onFinish() {
                    Intent i = new Intent(splash.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}