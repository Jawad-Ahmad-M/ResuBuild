package com.resumebuilder.app;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.link.LinkHandler;
import com.shockwave.pdfium.PdfDocument;

public class CustomLinkHandler implements LinkHandler {
    private static final String TAG = "CustomLinkHandler";
    private final Context context;
    private final PDFView pdfView;

    public CustomLinkHandler(Context context, PDFView pdfView) {
        this.context = context;
        this.pdfView = pdfView;
    }

    @Override
    public void handleLinkEvent(com.github.barteksc.pdfviewer.model.LinkTapEvent event) {
        PdfDocument.Link link = event.getLink(); // Direct field access
        if (link != null) {
            String uri = link.getUri();
            Integer destPageIdx = link.getDestPageIdx();

            if (uri != null && !uri.isEmpty()) {
                // External URL
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.e(TAG, "No app to handle URL: " + uri, e);
                    Toast.makeText(context, "No app to handle URL: " + uri, Toast.LENGTH_SHORT).show();
                }
            } else if (destPageIdx != null) {
                // Internal page jump
                pdfView.jumpTo(destPageIdx, true);
            } else {
                Toast.makeText(context, "Unsupported link type", Toast.LENGTH_SHORT).show();
            }
        }
    }
}