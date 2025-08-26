package com.resumebuilder.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.resumebuilder.app.R;
import com.resumebuilder.app.adapters.EducationAdapter;
import com.resumebuilder.app.itemClasses.Education;
import com.resumebuilder.app.testActivity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class detailsFragment extends Fragment {

    private final List<Education> educationList = new ArrayList<>();

    private RecyclerView rvEducation;
    private EducationAdapter educationAdapter;

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

        EditText name = view.findViewById(R.id.et_name);
        EditText location = view.findViewById(R.id.et_location);
        EditText emailAddress = view.findViewById(R.id.et_email);
        EditText contactNumber = view.findViewById(R.id.et_contact);
        EditText linkedInProfile = view.findViewById(R.id.et_linkedin);
        EditText githubProfile = view.findViewById(R.id.et_github);

        Button btnAddEducation = view.findViewById(R.id.btn_add_education);
        btnAddEducation.setOnClickListener(v -> {
            educationList.add(new Education());
            educationAdapter.notifyItemInserted(educationList.size() - 1);
        });

        rvEducation = view.findViewById(R.id.rv_education);
        rvEducation.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadEducationData();

        educationAdapter = new EducationAdapter(educationList, requireContext());
        rvEducation.setAdapter(educationAdapter);

        btnGetView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), testActivity.class);
            intent.putExtra("pdf_assets", "RenderCV_sb2nov_Theme.pdf");
            startActivity(intent);
        });
        return view;
    }

    private void loadEducationData() {
        // Load or initialize your education data
        educationList.clear();
        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
    }
}