package com.example.my_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CollectorActivity extends AppCompatActivity {
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);

        TextView tvCollectorTitle = findViewById(R.id.tvCollectorTitle);
        tvCollectorTitle.setText("Welcome, Collector!");

        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // تسجيل الخروج وإعادة المستخدم إلى شاشة التسجيل
                logoutCollector();
            }
        });
    }

    private void logoutCollector() {
        // حذف بيانات المستخدم وإعادة التوجيه إلى شاشة التسجيل
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(CollectorActivity.this, "Logged out successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CollectorActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish(); // إنهاء النشاط الحالي لمنع الرجوع للخلف
    }
}
