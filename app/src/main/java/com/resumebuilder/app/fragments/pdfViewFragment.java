package com.resumebuilder.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLongPressListener;
import com.github.barteksc.pdfviewer.listener.OnTapListener;
import com.github.barteksc.pdfviewer.util.FitPolicy;
import com.resumebuilder.app.R;

public class pdfViewFragment extends Fragment {

    public pdfViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_pdf_view, container, false);

        PDFView pdfView = rootView.findViewById(R.id.pdfView);

        pdfView.fromAsset("RenderCV_sb2nov_Theme.pdf")  // Name of the PDF in the assets folder
                .enableSwipe(true)            // Enable swipe for navigation
                .swipeHorizontal(true)        // Horizontal swipe between pages
                .enableDoubletap(true)        // Enable zoom on double tap
                .fitEachPage(true)            // Fit each page on screen with margins
                .pageFitPolicy(FitPolicy.BOTH) // Fit both width and height with margins
                .defaultPage(0)               // Start at the first page
                .load();


        return rootView;
    }
}