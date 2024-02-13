package com.example.mediclick;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

public class NutritionalAnalysis extends HealthTipsActivity {

    private ConstraintLayout layout1;
    private ConstraintLayout layout2;
    private ConstraintLayout layout3;
    private ConstraintLayout constraintLayout;
    private ConstraintLayout constraintLayout2;
    private ConstraintLayout constraintLayout3;
    private ConstraintLayout constraintLayout4;
   //rivate ActivityResultLauncher<String> filePickerLauncher;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button1;
    private Button button2;
    private Button button3;
    private ProgressBar progressBar;

    private EditText queryEditText;
    private ConstraintLayout R1;


    public NutritionalAnalysis() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_analysis);
        // Find the ProgressBar by its ID
        ProgressBar progressBar = findViewById(R.id.progressBar);
        EditText queryEditText = findViewById(R.id.editTextContainer1);
        // Find the ConstraintLayouts by their IDs
        layout1 = findViewById(R.id.Layout1);
        layout2 = findViewById(R.id.Layout2);
        layout3 = findViewById(R.id.Layout3);

        R1 = findViewById(R.id.relativeLayout1);

        // Find the TextViews by their IDs
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);
        TextView textViewa = findViewById(R.id.textViewa);
        TextView textViewb = findViewById(R.id.textViewb);
        TextView textViewc = findViewById(R.id.textViewc);
        // Find the Button by its ID
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        // Find the ConstraintLayout by its ID
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout2 = findViewById(R.id.constraintLayout2);
        constraintLayout3 = findViewById(R.id.constraintLayout3);
        constraintLayout4 = findViewById(R.id.constraintLayout4);

        button1.setOnClickListener(v -> {
            R1.setVisibility(View.GONE);
            constraintLayout.setVisibility(View.GONE);
            String userQuery = queryEditText.getText().toString().trim();
            progressBar.setVisibility(View.VISIBLE); // Show progress bar
            // Simulate a delay for demonstration purposes
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                analyzeQuery(userQuery);
                progressBar.setVisibility(View.GONE); // Hide progress bar
            }, 5000); // Adjust this delay as needed

            analyzeQuery(userQuery);
            // Create an intent and pass the user query to the next activity
            Intent intent = new Intent(NutritionalAnalysis.this, NA1.class);
            intent.putExtra("userQuery", userQuery);
            startActivity(intent);
        });
        button2.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionalAnalysis.this, NA2.class);
            startActivity(intent);
        });
        button3.setOnClickListener(v -> {
            Intent intent = new Intent(NutritionalAnalysis.this, NA3.class);
            startActivity(intent);
        });

        // Set click listeners for each View
        textViewa.setOnClickListener(v -> {
            // Handle the click for Layout1
            updateLayoutBackground(layout1, R.drawable.round_b);
            updateTextColor(textView1, R.color.white);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.VISIBLE);
        });

        textViewb.setOnClickListener(v -> {
            // Handle the click for Layout1
            updateLayoutBackground(layout2, R.drawable.round_b);
            updateTextColor(textView2, R.color.white);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.VISIBLE);
            button6.setVisibility(View.VISIBLE);
        });

        textViewc.setOnClickListener(v -> {
            // Handle the click for Layout1
            updateLayoutBackground(layout3, R.drawable.round_b);
            updateTextColor(textView3, R.color.white);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout3.setVisibility(View.VISIBLE);
            button5.setVisibility(View.VISIBLE);
        });

        textView1.setOnClickListener(v -> {
            // Handle the click for textView1
            updateLayoutBackground(layout1, R.drawable.round_b);
            updateTextColor(textView1, R.color.white);

            // Reset other layouts and text colors to default state
            updateLayoutBackground(layout2, R.drawable.round);
            updateLayoutBackground(layout3, R.drawable.round);
            updateTextColor(textView2, R.color.black);
            updateTextColor(textView3, R.color.black);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.VISIBLE);
            constraintLayout3.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.GONE);
        });

        textView2.setOnClickListener(v -> {
            // Handle the click for textView2
            updateLayoutBackground(layout2, R.drawable.round_b);
            updateTextColor(textView2, R.color.white); // Change text color

            // Reset other layouts and text colors to default state
            updateLayoutBackground(layout1, R.drawable.round);
            updateLayoutBackground(layout3, R.drawable.round);
            updateTextColor(textView1, R.color.black);
            updateTextColor(textView3, R.color.black);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.VISIBLE);
            constraintLayout2.setVisibility(View.GONE);
            constraintLayout3.setVisibility(View.GONE);
        });

        textView3.setOnClickListener(v -> {
            // Handle the click for textView3
            updateLayoutBackground(layout3, R.drawable.round_b);
            updateTextColor(textView3, R.color.white); // Change text color

            // Reset other layouts and text colors to default state
            updateLayoutBackground(layout1, R.drawable.round);
            updateLayoutBackground(layout2, R.drawable.round);
            updateTextColor(textView1, R.color.black);
            updateTextColor(textView2, R.color.black);

            // Hide existing views
            constraintLayout.setVisibility(View.GONE);
            constraintLayout3.setVisibility(View.VISIBLE);
            constraintLayout2.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.GONE);
        });

        button4.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.VISIBLE);
            constraintLayout3.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.GONE);
        });

        button5.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.VISIBLE);
            constraintLayout3.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.GONE);
        });

        button6.setOnClickListener(v -> {
            constraintLayout.setVisibility(View.VISIBLE);
            constraintLayout3.setVisibility(View.GONE);
            constraintLayout2.setVisibility(View.GONE);
            constraintLayout4.setVisibility(View.GONE);
        });
    }

    private void updateLayoutBackground(@NonNull ConstraintLayout layout, int bgDrawableResId) {
        layout.setBackgroundResource(bgDrawableResId);
    }

    private void updateTextColor(@NonNull TextView textView, int textColorResId) {
        textView.setTextColor(ContextCompat.getColor(this, textColorResId));
    }

    private void analyzeQuery(String userQuery) {
        Log.d(TAG, "User Query: " + userQuery);

        if (!userQuery.isEmpty()) {
            if (userQuery.contains(",")) {
                // Show an error message for using commas in the query
                Toast.makeText(this, "Do not use commas between items instead use spaces", Toast.LENGTH_SHORT).show();
            } else if (userQuery.length() > 1500) {
                // Show an error message if query exceeds character limit
                Toast.makeText(this, "Query cannot exceed 1500 characters", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
