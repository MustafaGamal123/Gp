package com.example.my_app;

import static com.example.my_app.R.id.listViewAlerts;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

public class AlertsNotificationsActivity extends AppCompatActivity {
    private ListView listViewAlerts;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> alertsList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts_notifications);

        listViewAlerts = findViewById(R.id.listViewAlerts);

        // قائمة تجريبية للإشعارات
        alertsList = new ArrayList<>();
        alertsList.add("⚠️ Bin is almost full! Please empty it soon.");
        alertsList.add("🌍 Air quality in your area has dropped. Consider recycling more.");
        alertsList.add("✅ Your last recycling session saved 3 kg of plastic!");
        alertsList.add("🔔 Reminder: Check your recycling history for rewards.");
        alertsList.add("📢 New feature: Voice commands are now available!");

        // إنشاء محول بيانات لعرض القائمة
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alertsList);
        listViewAlerts.setAdapter(adapter);
    }
}
