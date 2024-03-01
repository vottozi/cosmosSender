package com.example.cosmosmob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.cosmosmob.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {


    LottieAnimationView lotty_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lotty_s = findViewById(R.id.imageM);
        lotty_s.setAnimation(R.raw.animation_fire);
        lotty_s.playAnimation();

        lotty_s.animate().alpha(1f).setDuration(3000).withEndAction(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}