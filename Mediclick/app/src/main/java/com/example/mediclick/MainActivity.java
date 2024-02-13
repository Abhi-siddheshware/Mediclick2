package com.example.mediclick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        RelativeLayout containerLayout = findViewById(R.id.containerLayout);
        containerLayout.setOnClickListener(v -> {
            // Handle the click action here, navigate to HealthTipsActivity
            Intent intent = new Intent(MainActivity.this, HealthTipsActivity.class);
            startActivity(intent);
        });
    }
}
