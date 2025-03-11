package com.example.my_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DURATION = 2000; // 2 ثانية

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView splashImage = findViewById(R.id.splash_image);
        TextView splashText = findViewById(R.id.splash_text);

        // تأثير Fade-In على الصورة والنص
        Animation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1500);
        splashImage.startAnimation(fadeIn);
        splashText.startAnimation(fadeIn);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            String userType = sharedPreferences.getString("USER_TYPE", "GUEST");

            Intent intent;
            switch (userType) {
                case "ADMIN":
                    intent = new Intent(SplashActivity.this, AdminActivity.class);
                    break;
                case "COLLECTOR":
                    intent = new Intent(SplashActivity.this, CollectorActivity.class);
                    break;
                case "USER":
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    break;
                default:
                    intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                    break;
            }

            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, SPLASH_DURATION);
    }
}
