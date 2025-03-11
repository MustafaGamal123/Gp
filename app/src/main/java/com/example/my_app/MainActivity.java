package com.example.my_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.my_app.databinding.ActivityMainBinding;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("my_app");
    }

    private ActivityMainBinding binding;
    private final Map<Integer, Fragment> fragmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // تحميل اسم المستخدم من SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userName = sharedPreferences.getString("USER_NAME", "User");
        binding.tvUserName.setText("Hello, " + userName + "!");

        // إعداد الـ Fragments في الـ HashMap
        fragmentMap.put(R.id.nav_welcome, new WelcomeFragment());
        fragmentMap.put(R.id.nav_rewards, new RewardsFragment());
        fragmentMap.put(R.id.nav_qr, new QRFragment());
        fragmentMap.put(R.id.nav_games, new GamesFragment());
        fragmentMap.put(R.id.nav_voice, new VoiceFragment());

        // تحميل الشاشة الافتراضية (WelcomeFragment)
        loadFragment(fragmentMap.get(R.id.nav_welcome));

        // تحديد العنصر الافتراضي في Bottom Navigation
        binding.bottomNavigationView.setSelectedItemId(R.id.nav_welcome);

        // إعداد التنقل بين الـ Fragments باستخدام HashMap
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = fragmentMap.get(item.getItemId());
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }

    public native String stringFromJNI();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_capacity) {
            startActivity(new Intent(this, CapacityActivity.class));
            return true;
        } else if (id == R.id.menu_location) {
            startActivity(new Intent(this, LocationActivity.class));
            return true;
        } else if (id == R.id.menu_environmental_data) {
            startActivity(new Intent(this, EnvironmentalDataActivity.class));
            return true;
        } else if (id == R.id.menu_alerts_notifications) {
            startActivity(new Intent(this, AlertsNotificationsActivity.class));
            return true;
        } else if (id == R.id.menu_type_of_bin) {
            startActivity(new Intent(this, TypeOfBinActivity.class));
            return true;
        } else if (id == R.id.menu_tips) {
            startActivity(new Intent(this, TipsActivity.class));
            return true;
        } else if (id == R.id.menu_logout) {
            logoutUser();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
        startActivity(intent);
        finish();
    }
}
