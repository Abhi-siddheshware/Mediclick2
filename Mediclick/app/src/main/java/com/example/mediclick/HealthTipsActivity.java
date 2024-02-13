package com.example.mediclick;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class HealthTipsActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.health_tips);

        // Initialize views
        RelativeLayout r1 = findViewById(R.id.R1);
        RelativeLayout r2 = findViewById(R.id.R2);
        RelativeLayout r3 = findViewById(R.id.R3);
        RelativeLayout r4 = findViewById(R.id.R4);

        // Set click listeners for the TextViews
        r1.setOnClickListener(v -> {
            Intent intent = new Intent(HealthTipsActivity.this, NutritionalAnalysis.class);
            startActivity(intent);
        });

        r2.setOnClickListener(v -> {
            Intent intent = new Intent(HealthTipsActivity.this, ExerciseResources.class);
            startActivity(intent);
        });

        r3.setOnClickListener(v -> {
            Intent intent = new Intent(HealthTipsActivity.this, WeatherTips.class);
            startActivity(intent);
        });

        r4.setOnClickListener(v -> {
            Intent intent = new Intent(HealthTipsActivity.this, YogaAndMeditation.class);
            startActivity(intent);
        });

        // Call initialization method
        initState();
    }

    private void initState() {
        // Initialization code here
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Call dispose method
        dispose();
    }

    private void dispose() {
        // Disposal code here
    }

    // Action blocks (methods) here

    // Additional helper methods here
}
