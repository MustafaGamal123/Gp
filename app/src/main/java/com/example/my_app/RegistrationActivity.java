package com.example.my_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private EditText editTextName, editTextAge, editTextPassword;
    private RadioGroup radioGroupGender;
    private Button buttonContinue, buttonLogout;
    private static final String TAG = "RegistrationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPassword = findViewById(R.id.editTextPassword);
        radioGroupGender = findViewById(R.id.radioGroupGender);
        buttonContinue = findViewById(R.id.buttonContinue);
        buttonLogout = findViewById(R.id.buttonLogout);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
    }

    private void handleLogin() {
        try {
            String name = editTextName.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if (name.equals("admin") && password.equals("admin")) {
                Log.d(TAG, "Admin login successful.");
                editor.putString("USER_TYPE", "ADMIN");
                editor.apply();
                navigateToActivity(AdminActivity.class);
                return;
            }

            if (name.equals("collector") && password.equals("collector")) {
                Log.d(TAG, "Collector login successful.");
                editor.putString("USER_TYPE", "COLLECTOR");
                editor.apply();
                navigateToActivity(CollectorActivity.class);
                return;
            }

            String age = editTextAge.getText().toString().trim();
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();

            if (name.isEmpty() || age.isEmpty() || password.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "User did not fill all fields.");
                return;
            }

            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            editor.putString("USER_TYPE", "USER");
            editor.putString("USER_NAME", name);
            editor.putString("USER_AGE", age);
            editor.putString("USER_GENDER", gender);
            editor.apply();

            navigateToActivity(MainActivity.class);

        } catch (Exception e) {
            Log.e(TAG, "Error in RegistrationActivity: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(RegistrationActivity.this, "An error occurred!", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(RegistrationActivity.this, activityClass);
        startActivity(intent);
        finish();
    }

    private void logoutUser() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Toast.makeText(RegistrationActivity.this, "Logged out successfully!", Toast.LENGTH_SHORT).show();
    }
}
