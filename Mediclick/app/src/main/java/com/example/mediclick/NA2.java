package com.example.mediclick;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class NA2 extends NutritionalAnalysis {

    private static final int PICK_FILE = 1001; // Update the request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_analysis);

        Button browseButton = findViewById(R.id.browseButton);
        browseButton.setOnClickListener(v -> openGalleryForFiles());
    }

    private void openGalleryForFiles() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("*/*"); // Set the MIME type to allow any file type
        resultLauncher.launch(galleryIntent);
    }

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        Uri selectedFileUri = data.getData();
                        // Do something with the selected file URI
                        handleSelectedFile(selectedFileUri);
                    }
                }
            });

    private void handleSelectedFile(Uri fileUri) {
        // Implement your logic to handle the selected file here
        // For example, you might want to display the file name or perform further actions
    }
}
