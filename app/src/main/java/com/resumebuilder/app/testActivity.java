package com.resumebuilder.app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;

import java.util.Locale;

public class testActivity extends AppCompatActivity implements OnLoadCompleteListener, OnPageChangeListener {

    private static final String TAG = "testActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_view);

        // Get the PDF file name from Intent
        String assetFileName = getIntent().getStringExtra("pdf_assets");
        Log.d(TAG, "Asset file: " + assetFileName);

        // Initialize PDFView
        PDFView pdfView = findViewById(R.id.pdfView_activity);

        // Show file name for debugging
        Toast.makeText(this, "Loading PDF: " + (assetFileName != null ? assetFileName : "No file specified"), Toast.LENGTH_LONG).show();

        // Load PDF if file name is valid
        if (assetFileName != null && !assetFileName.isEmpty()) {
            try {
                pdfView.fromAsset(assetFileName)
                        // Vertical cards-like configuration
                        .swipeHorizontal(false)  // Vertical swiping for card-stack effect
                        .spacing(32)  // Increased to 32dp for more pronounced breaks between pages
                        .pageSnap(true)  // Snap to page boundaries
                        .autoSpacing(true)  // Adjust spacing for fit
                        .pageFling(true)  // Smooth fling animation
                        .pageFitPolicy(FitPolicy.BOTH)  // Fit each page to screen
                        .fitEachPage(true)  // Treat each page as a standalone "card"
                        // Link handling
                        .enableAnnotationRendering(true)  // Render links/annotations
                        .linkHandler(new CustomLinkHandler(this, pdfView))  // Handle link clicks
                        // Listeners
                        .onLoad(this)
                        .onPageChange(this)
                        .onError(t -> {
                            Log.e(TAG, "PDF load error: " + t.getMessage(), t);
                            Toast.makeText(this, "Error loading PDF: " + t.getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        })
                        .defaultPage(0)
                        .enableAntialiasing(true)
                        .load();
            } catch (Exception e) {
                Log.e(TAG, "Exception loading PDF: " + e.getMessage(), e);
                Toast.makeText(this, "Failed to load PDF: " + e.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            Toast.makeText(this, "No PDF file specified", Toast.LENGTH_LONG).show();
            finish();
        }

        // Back button
        ImageButton buttonBack = findViewById(R.id.btn_back);
        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText(this, "PDF loaded with " + nbPages + " pages", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        setTitle(String.format(Locale.US, "Page %d of %d", page + 1, pageCount));
    }
}
