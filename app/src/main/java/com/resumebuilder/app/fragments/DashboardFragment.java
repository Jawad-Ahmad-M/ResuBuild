package com.resumebuilder.app.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.resumebuilder.app.adapters.ResumeAdapter;
import com.resumebuilder.app.database.AppDatabase;
import com.resumebuilder.app.databinding.FragmentDashboardBinding;
import com.resumebuilder.app.itemClasses.Resume;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DashboardFragment extends Fragment {
    private static ExecutorService executorService;
    private static AppDatabase db;
    private FragmentDashboardBinding binding;
    private ResumeAdapter resumeAdapter;
    private final List<Resume> resumeList = new ArrayList<>();

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        executorService = Executors.newFixedThreadPool(5);
        db = AppDatabase.getInstance(view.getContext());

        loadResumeData();

        binding.rvResumeItems.setLayoutManager(new LinearLayoutManager(getContext()));
        resumeAdapter = new ResumeAdapter(resumeList,getContext());
        binding.rvResumeItems.setAdapter(resumeAdapter);

        binding.btnAddResume.setOnClickListener(v -> {
            long timestamp = System.currentTimeMillis();
            Date date = new Date(timestamp);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            resumeList.add(new Resume(sdf.format(date)));
            resumeAdapter.notifyItemInserted(resumeList.size() + 1);
            Log.d("Room DB", resumeList.toString());
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadResumeData(){
        executorService.execute(()->{
            List<Resume> list = db.resumeDao().getAllResume();

            requireActivity().runOnUiThread(() ->{
                resumeList.addAll(list);
                resumeAdapter.notifyDataSetChanged();
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}