package com.resumebuilder.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.github.barteksc.pdfviewer.PDFView;
import com.resumebuilder.app.GetViewActivity;
import com.resumebuilder.app.R;
import com.resumebuilder.app.testActivity;

public class detailsFragment extends Fragment {

    public detailsFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_details, container, false);

        Button btnGetView = view.findViewById(R.id.btn_get_view);
        btnGetView.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), testActivity.class);
            intent.putExtra("pdf_assets", "RenderCV_sb2nov_Theme.pdf");
//            intent.putExtra("pdf_assets", "androidDeveloperCV.pdf");
            startActivity(intent);
        });

        return view;
    }
}