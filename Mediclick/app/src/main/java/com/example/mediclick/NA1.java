package com.example.mediclick;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NA1 extends NutritionalAnalysis {

    private static final String TAG = "NutritionalAnalysis";
    private ProgressBar progressBar;
    private ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n_a_1);

        constraintLayout=findViewById(R.id.CL);
        progressBar = findViewById(R.id.progressBar);

        String userQuery = getIntent().getStringExtra("userQuery");

        if (isValidQuery(userQuery)) {
            fetchNutritionData(userQuery);
        } else {
            displayInvalidQueryError();
        }
    }

    private boolean isValidQuery(String userQuery) {
        return userQuery != null && !userQuery.isEmpty() && !userQuery.contains(",") && userQuery.length() <= 1500;
    }

    private void fetchNutritionData(String userQuery) {
            String apiKey = "T61f2/PVbqLwoFNOmne84g==uFyHBIbk4v3aeQqQ";
            String apiUrl = "https://api.calorieninjas.com/v1/nutrition?query=" + userQuery;

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(apiUrl)
                    .addHeader("X-Api-Key", apiKey)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            String responseBody = response.body().string();
                            JSONObject responseObject = new JSONObject(responseBody);

                            if (responseObject.has("items")) {
                                JSONArray jsonArray = responseObject.getJSONArray("items");

                                runOnUiThread(() -> {
                                    try {
                                        logExtractedData(jsonArray);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    // Hide the progress bar after fetching and processing data
                                    progressBar.setVisibility(View.GONE);
                                    constraintLayout.setVisibility(View.VISIBLE);
                                });
                            } else {
                                Log.d(TAG, "No items found in the response");
                            }
                        } else {
                            Log.d(TAG, "Response not successful: " + response.code());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    e.printStackTrace();
                }
            });
        }
    private void logExtractedData(JSONArray jsonArray) throws JSONException {
        LinearLayout[] dynamicLinearLayouts = new LinearLayout[12];
        dynamicLinearLayouts[0] = findViewById(R.id.L0);
        dynamicLinearLayouts[1] = findViewById(R.id.L1);
        dynamicLinearLayouts[2] = findViewById(R.id.L2);
        dynamicLinearLayouts[3] = findViewById(R.id.L3);
        dynamicLinearLayouts[4] = findViewById(R.id.L4);
        dynamicLinearLayouts[5] = findViewById(R.id.L5);
        dynamicLinearLayouts[6] = findViewById(R.id.L6);
        dynamicLinearLayouts[7] = findViewById(R.id.L7);
        dynamicLinearLayouts[8] = findViewById(R.id.L8);
        dynamicLinearLayouts[9] = findViewById(R.id.L9);
        dynamicLinearLayouts[10] = findViewById(R.id.L10);
        dynamicLinearLayouts[11] = findViewById(R.id.L11);

        int itemCount = jsonArray.length();

        String[] nutrientKeys = {
                "name", "serving_size_g", "calories", "sugar_g", "protein_g", "cholesterol_mg",
                "sodium_mg", "carbohydrates_total_g", "fiber_g", "potassium_mg", "fat_total_g", "fat_saturated_g"
        };

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject itemObject = jsonArray.getJSONObject(i);

            for (String nutrientKey : nutrientKeys) {
                LinearLayout itemLayout = findLayoutForNutrient(dynamicLinearLayouts, nutrientKey);
                if (itemLayout == null) {
                    continue; // Skip this iteration if layout is null
                }
                String nutrientValue = itemObject.getString(nutrientKey);

                if (nutrientKey.contains("_g")) {
                    nutrientValue = nutrientValue + " g";
                } else if (nutrientKey.contains("_mg")) {
                    nutrientValue = nutrientValue + " mg";
                }

                if (nutrientKey.equals("name")) {
                    addTextViewToLayout1(itemLayout, nutrientValue, itemCount);
                } else {
                    addTextViewToLayout(itemLayout, nutrientValue, itemCount);
                }
            }
        }
    }
    private LinearLayout findLayoutForNutrient(LinearLayout[] layouts, String nutrientKey) {
        switch (nutrientKey) {
            case "name":
                return layouts[0];
            case "serving_size_g":
                return layouts[1];
            case "calories":
                return layouts[2];
            case "sugar_g":
                return layouts[3];
            case "protein_g":
                return layouts[4];
            case "cholesterol_mg":
                return layouts[5];
            case "sodium_mg":
                return layouts[6];
            case "carbohydrates_total_g":
                return layouts[7];
            case "fiber_g":
                return layouts[8];
            case "potassium_mg":
                return layouts[9];
            case "fat_total_g":
                return layouts[10];
            case "fat_saturated_g":
                return layouts[11];
            default:
                return null;
        }
    }

    private void addTextViewToLayout1(LinearLayout layout, String text, int itemCount) {
        // Create a new TextView
        TextView textView = new TextView(this);

        // Set the layout parameters for the new TextView
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textView.setLayoutParams(layoutParams);

        // Set the text for the TextView
        textView.setText(text);

        // Apply properties to match your desired style
        textView.setGravity(Gravity.CENTER);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.lato);
        textView.setTypeface(typeface);

        // Set the text color to black
        int textColor = ContextCompat.getColor(this, R.color.black);
        textView.setTextColor(textColor);

        // Add the TextView to the layout
        layout.addView(textView);

        // Adjust layout parameters for the new TextView
        LinearLayout.LayoutParams newLayoutParams = new LinearLayout.LayoutParams(
                250, // width in pixels
                50   // height in pixels
        );
        newLayoutParams.gravity = Gravity.CENTER;
        newLayoutParams.weight = 0; // No weight for the new TextView

        // Apply updated layout parameters to the new TextView
        textView.setLayoutParams(newLayoutParams);

        // Calculate the new width increment based on the item count
        int existingTextViewWidth = layout.getChildCount() * 300; // Assuming fixed width of 250

        // Calculate the new width increment based on the item count and existing TextView width
        int widthIncrement = (itemCount - layout.getChildCount()) * 50;

        // Calculate the new width for the parent layout
        ViewGroup.LayoutParams parentLayoutParams = layout.getLayoutParams();
        if (parentLayoutParams != null) {
            parentLayoutParams.width = existingTextViewWidth + widthIncrement;
            layout.setLayoutParams(parentLayoutParams);
        }
    }


    private void addTextViewToLayout(LinearLayout layout, String text, int itemCount) {
        // Create a new TextView
        TextView textView = new TextView(this);

        // Set the layout parameters for the new TextView
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        textView.setLayoutParams(layoutParams);

        // Set the text for the TextView
        textView.setText(text);

        // Apply properties to match your desired style
        textView.setGravity(Gravity.CENTER);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.lato);
        textView.setTypeface(typeface);

        // Get the color from resources using ContextCompat
        int textColor = ContextCompat.getColor(this, R.color.white);
        textView.setTextColor(textColor);

        // Add the new TextView to the layout
        layout.addView(textView);

        // Adjust layout parameters for the new TextView
        LinearLayout.LayoutParams newLayoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        newLayoutParams.width = 250; // Set width to 100 pixels
        newLayoutParams.height = 40; // Set height to 34 pixels
        newLayoutParams.gravity = Gravity.CENTER;
        newLayoutParams.weight = 0;   // No weight for the first TextView

        // Apply updated layout parameters to the new TextView
        textView.setLayoutParams(newLayoutParams);

        // Calculate the width of existing TextViews
        int existingTextViewWidth = layout.getChildCount() * 300; // Assuming fixed width of 250

        // Calculate the new width increment based on the item count and existing TextView width
        int widthIncrement = (itemCount - layout.getChildCount()) * 50;

        // Calculate the new width for the parent layout
        ViewGroup.LayoutParams parentLayoutParams = layout.getLayoutParams();
        if (parentLayoutParams != null) {
            parentLayoutParams.width = existingTextViewWidth + widthIncrement;
            layout.setLayoutParams(parentLayoutParams);
        }
    }

    private void displayInvalidQueryError() {
        Toast.makeText(this, "Invalid query entered", Toast.LENGTH_SHORT).show();
        finish();
    }
    // Rest of your code
}
