package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;

public class EnvironmentalDataActivity extends AppCompatActivity {
    private TextView tvTemperature, tvAirQuality, tvPollutionLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environmental_data);

        tvTemperature = findViewById(R.id.tvTemperature);
        tvAirQuality = findViewById(R.id.tvAirQuality);
        tvPollutionLevel = findViewById(R.id.tvPollutionLevel);

        // محاكاة بيانات بيئية (يمكن لاحقًا استبدالها ببيانات فعلية من API)
        updateEnvironmentalData();
    }

    private void updateEnvironmentalData() {
        Random random = new Random();

        int temperature = 20 + random.nextInt(15); // قيمة عشوائية بين 20 و 35 درجة
        int airQualityIndex = 50 + random.nextInt(100); // قيمة عشوائية بين 50 و 150
        int pollutionLevel = random.nextInt(100); // نسبة التلوث بين 0 و 100%

        tvTemperature.setText("🌡️ Temperature: " + temperature + "°C");
        tvAirQuality.setText("🌍 Air Quality Index: " + airQualityIndex);
        tvPollutionLevel.setText("☁️ Pollution Level: " + pollutionLevel + "%");

        // تغيير لون جودة الهواء بناءً على مستوى التلوث
        if (airQualityIndex > 100) {
            tvAirQuality.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        } else if (airQualityIndex > 70) {
            tvAirQuality.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            tvAirQuality.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        }
    }
}
