package com.resumebuilder.app.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.resumebuilder.app.CustomViews.CircularATSScoreView;
import com.resumebuilder.app.R;

public class atsScoreFragment extends Fragment {

    private int score;
    private int newScore;
    private CircularATSScoreView circularATSScoreView;
    private TextView tvAtsScore;
    public atsScoreFragment() {
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
        View view = inflater.inflate(R.layout.fragment_ats_score, container, false);

        score = 70;
        newScore = score;
        circularATSScoreView = view.findViewById(R.id.circular_ats_score_view);
        tvAtsScore = view.findViewById(R.id.tv_ats_score);

        display(score);


        Button plus, minus, recheckAtsScore;
        plus = view.findViewById(R.id.btn_plus_2);
        minus = view.findViewById(R.id.btn_minus_2);
        recheckAtsScore = view.findViewById(R.id.btn_rechecking_ats_score);


        plus.setOnClickListener(v -> {
            newScore = newScore + 2;
            display(newScore);
        });

        minus.setOnClickListener(v -> {
            newScore = newScore - 2;
            display(newScore);
        });

        recheckAtsScore.setOnClickListener(v -> display(score));
        return view;
    }

    private void display(int score){
        circularATSScoreView.setScore(score);
        String atsScore = "ATS Score\n" + score;
        tvAtsScore.setText(atsScore);
    }
}