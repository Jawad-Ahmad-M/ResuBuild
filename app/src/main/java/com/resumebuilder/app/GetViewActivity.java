package com.resumebuilder.app;


import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.util.FitPolicy;


public class GetViewActivity extends AppCompatActivity {

    private static final String TAG = "GetViewActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_view);

        String assetFileName = getIntent().getStringExtra("pdf_assets");


        PDFView pdfView = findViewById(R.id.pdfView_activity);

        Toast.makeText(this, assetFileName, Toast.LENGTH_LONG).show();

        if (assetFileName != null) {

            pdfView.fromAsset(assetFileName)  // Name of the PDF in the assets folder
                    .enableSwipe(true) // allows swiping between pages
                    .swipeHorizontal(false) // false for vertical scrolling
                    .enableDoubletap(true)
                    // Link handling
                    .enableAnnotationRendering(true)  // Render links/annotations
                    .linkHandler(new CustomLinkHandler(this, pdfView))  // Handle link clicks
                    .onError(t -> {
                        Log.e(TAG, "PDF load error: " + t.getMessage(), t);
                        Toast.makeText(this, "Error loading PDF: " + t.getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    })
                    .defaultPage(0)
                    .spacing(10) // **This is key for Word-like page breaks**
                    .pageFitPolicy(FitPolicy.WIDTH) // fit pages by width
                    .load();
        } else {
            Toast.makeText(this,"No PDF Name was Given", Toast.LENGTH_LONG).show();
        }

        ImageButton buttonBack = findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

    }
}

