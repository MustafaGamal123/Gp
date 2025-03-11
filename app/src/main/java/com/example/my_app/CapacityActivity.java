package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CapacityActivity extends AppCompatActivity {
    private ProgressBar progressBarCapacity;
    private TextView tvCapacityStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capacity);

        progressBarCapacity = findViewById(R.id.progressBarCapacity);
        tvCapacityStatus = findViewById(R.id.tvCapacityStatus);

        // محاكاة نسبة الامتلاء (يمكن استبدالها بقيمة حقيقية من مستشعر السلة الذكية)
        int currentCapacity = 65; // قيمة تجريبية، يمكن استبدالها بقراءة حقيقية
        updateCapacityUI(currentCapacity);
    }

    private void updateCapacityUI(int capacityPercentage) {
        progressBarCapacity.setProgress(capacityPercentage);
        tvCapacityStatus.setText("Current Capacity: " + capacityPercentage + "%");

        if (capacityPercentage > 80) {
            tvCapacityStatus.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (capacityPercentage > 50) {
            tvCapacityStatus.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            tvCapacityStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}
