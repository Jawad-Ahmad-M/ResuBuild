package com.resumebuilder.app.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.resumebuilder.app.adapters.CertificationAdapter;
import com.resumebuilder.app.adapters.EducationAdapter;
import com.resumebuilder.app.adapters.ExperienceAdapter;
import com.resumebuilder.app.adapters.ProjectAdapter;
import com.resumebuilder.app.adapters.SkillAdapter;
import com.resumebuilder.app.databinding.FragmentDetailsBinding;
import com.resumebuilder.app.itemClasses.Certification;
import com.resumebuilder.app.itemClasses.Education;
import com.resumebuilder.app.itemClasses.Experience;
import com.resumebuilder.app.itemClasses.Project;
import com.resumebuilder.app.itemClasses.Skill;
import com.resumebuilder.app.testActivity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class detailsFragment extends Fragment {

    private FragmentDetailsBinding binding;
    private final List<Education> educationList = new ArrayList<>();
    private final List<Experience> experienceList = new ArrayList<>();
    private final List<Project> projectList = new ArrayList<>();
    private final List<Skill> skillList = new ArrayList<>();
    private final List<Certification> certificationList = new ArrayList<>();

    private EducationAdapter educationAdapter;
    private ExperienceAdapter experienceAdapter;
    private ProjectAdapter projectAdapter;

    public detailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        educationAdapter = new EducationAdapter(educationList, requireContext());
        experienceAdapter = new ExperienceAdapter(experienceList, requireContext());
        projectAdapter = new ProjectAdapter(projectList, requireContext());
        SkillAdapter skillAdapter = new SkillAdapter(skillList);
        CertificationAdapter certificationAdapter = new CertificationAdapter(certificationList);

        binding.btnGetView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), testActivity.class);
            intent.putExtra("pdf_assets", "RenderCV_sb2nov_Theme.pdf");
            startActivity(intent);
        });

//        EditText name = view.findViewById(R.id.et_name);
//        EditText location = view.findViewById(R.id.et_location);
//        EditText emailAddress = view.findViewById(R.id.et_email);
//        EditText contactNumber = view.findViewById(R.id.et_contact);
//        EditText linkedInProfile = view.findViewById(R.id.et_linkedin);
//        EditText githubProfile = view.findViewById(R.id.et_github);


        binding.btnAddEducation.setOnClickListener(v -> {
            educationList.add(new Education());
            educationAdapter.notifyItemInserted(educationList.size() - 1);
        });

        binding.btnAddExperience.setOnClickListener(v -> {
            experienceList.add(new Experience());
            experienceAdapter.notifyItemInserted(experienceList.size() - 1);
        });

        binding.btnAddProjects.setOnClickListener(v -> {
            projectList.add(new Project());
            projectAdapter.notifyItemInserted(projectList.size() - 1);
        });

        binding.btnAddSkill.setOnClickListener(v -> {
            skillList.add(new Skill());
            skillAdapter.notifyItemInserted(skillList.size() - 1);
        });

        binding.btnAddCertification.setOnClickListener(v -> {
            certificationList.add(new Certification());
            certificationAdapter.notifyItemInserted(certificationList.size() - 1);
        });

        binding.rvEducation.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadEducationData();

        binding.rvExperience.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadExperienceData();

        binding.rvProjects.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadProjectData();

        binding.rvSkills.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadSkillData();

        binding.rvCertifications.setLayoutManager(new LinearLayoutManager(requireContext()));
        loadCertificationData();

        binding.rvEducation.setAdapter(educationAdapter);
        binding.rvExperience.setAdapter(experienceAdapter);
        binding.rvProjects.setAdapter(projectAdapter);
        binding.rvSkills.setAdapter(skillAdapter);
        binding.rvCertifications.setAdapter(certificationAdapter);
    }

    private void loadEducationData() {
        // Load or initialize your education data
        educationList.clear();
        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
    }

    private void loadExperienceData() {
        experienceList.clear();
        experienceList.add(new Experience("Software Engineer", "San Francisco, CA", YearMonth.of(2020,3), YearMonth.of(2023,6), "Google", "Worked on developing scalable backend services."));
        experienceList.add(new Experience("Senior Developer", "New York, NY", YearMonth.of(2020,3), YearMonth.of(2023,6), "Facebook", "Leading a team on mobile app development."));
    }

    private void loadProjectData() {
        projectList.clear();
        projectList.add(new Project("AI Chatbot Integration", "OpenAI", YearMonth.of(2021, 5), YearMonth.of(2022, 12), "Developed and integrated an AI-powered chatbot into customer support systems to automate query handling."));
        projectList.add(new Project("E-Commerce Web Platform", "Amazon", YearMonth.of(2019, 8), YearMonth.of(2021, 3), "Built a scalable web-based e-commerce platform with payment gateway integration and advanced product recommendation system."));
    }

    private void loadSkillData() {
        skillList.clear();
        skillList.add(new Skill("Python Programming"));
        skillList.add(new Skill("Java Programming"));
    }

    private void loadCertificationData() {
        certificationList.clear();
        certificationList.add(new Certification("Google Cloud Certified – Associate Cloud Engineer", "Google Cloud", "Mar 2022", "https://www.credential.net/sample-google-cert"));
        certificationList.add(new Certification("AWS Certified Developer – Associate", "Amazon Web Services", "Aug 2021"));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}