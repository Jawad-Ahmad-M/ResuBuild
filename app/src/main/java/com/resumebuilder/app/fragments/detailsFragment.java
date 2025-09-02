package com.resumebuilder.app.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.resumebuilder.app.adapters.CertificationAdapter;
import com.resumebuilder.app.adapters.EducationAdapter;
import com.resumebuilder.app.adapters.ExperienceAdapter;
import com.resumebuilder.app.adapters.ProjectAdapter;
import com.resumebuilder.app.adapters.SkillAdapter;
import com.resumebuilder.app.database.AppDatabase;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class detailsFragment extends Fragment {

    private int heightPersonal;
    private int heightSummary;
    private int heightEducation;
    private int heightExperience;
    private int heightProjects;
    private int heightSkills;
    private int heightCertifications;

    private String DEBUG_TAG = "Room DB Services";

    private AppDatabase db;

    private boolean isSectionPersonalVisible = true;
    private boolean isSectionSummaryVisible  = true;
    private boolean isSectionEducationVisible  = true;
    private boolean isSectionExperienceVisible  = true;
    private boolean isSectionProjectsVisible  = true;
    private boolean isSectionSkillsVisible  = true;
    private boolean isSectionCertificationVisible = true;

    private FragmentDetailsBinding binding;
    private List<Education> educationList = new ArrayList<>();
    private final List<Experience> experienceList = new ArrayList<>();
    private final List<Project> projectList = new ArrayList<>();
    private final List<Skill> skillList = new ArrayList<>();
    private final List<Certification> certificationList = new ArrayList<>();

    private EducationAdapter educationAdapter;
    private ExperienceAdapter experienceAdapter;
    private ProjectAdapter projectAdapter;
    private SkillAdapter skillAdapter;
    private CertificationAdapter certificationAdapter;

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

        db = AppDatabase.getInstance(requireContext().getApplicationContext());

        educationAdapter = new EducationAdapter(educationList, requireContext());
        experienceAdapter = new ExperienceAdapter(experienceList, requireContext());
        projectAdapter = new ProjectAdapter(projectList, requireContext());
        skillAdapter = new SkillAdapter(skillList);
        certificationAdapter = new CertificationAdapter(certificationList);


        binding.rvEducation.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvEducation.setAdapter(educationAdapter);

        binding.rvExperience.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExperience.setAdapter(experienceAdapter);

        binding.rvProjects.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProjects.setAdapter(projectAdapter);

        binding.rvSkills.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSkills.setAdapter(skillAdapter);

        binding.rvCertifications.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCertifications.setAdapter(certificationAdapter);

//        loadEducationData();
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            db.educationDao().insertEducation(new Education("ABC", "abc","XYZ","Jul 2028","Jun 2027"));
            List<Education> list = db.educationDao().getAllEducation();
            educationList.addAll(list);
            Log.d(DEBUG_TAG, educationList.toString());
        });

        executorService.execute(()->{
            db.experienceDao().insertExperience(new Experience());
            List<Experience> list = db.experienceDao().getAllExperience();
            experienceList.addAll(list);
            Log.d(DEBUG_TAG, experienceList.toString());
        });

        executorService.execute(()->{
            db.projectDao().insertProject(new Project());
            List<Project> list = db.projectDao().getAllProjects();
            projectList.addAll(list);
            Log.d(DEBUG_TAG, projectList.toString());
        });

        executorService.execute(()->{
            db.skillsDao().insertSkill(new Skill());
            List<Skill> list = db.skillsDao().getAllSkills();
            skillList.addAll(list);
            Log.d(DEBUG_TAG, skillList.toString());
        });

        executorService.execute(()->{
            db.certificationDao().insertCertification(new Certification());
            List<Certification> list = db.certificationDao().getAllCertifications();
            certificationList.addAll(list);
            Log.d(DEBUG_TAG, certificationList.toString());
        });

        setFirstView();


        binding.btnGetView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), testActivity.class);
            intent.putExtra("pdf_assets", "RenderCV_sb2nov_Theme.pdf");
            startActivity(intent);
        });


        binding.btnExpandPersonal.setOnClickListener(v -> {
            isSectionPersonalVisible = !isSectionPersonalVisible;
            binding.contentPersonal.setVisibility(
                    isSectionPersonalVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandPersonal);
        });

        binding.btnExpandSummary.setOnClickListener(v -> {
            isSectionSummaryVisible = !isSectionSummaryVisible;
            binding.contentSummary.setVisibility(
                    isSectionSummaryVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandSummary);
        });

        binding.btnExpandEducation.setOnClickListener(v -> {
            isSectionEducationVisible = !isSectionEducationVisible;
            binding.contentEducation.setVisibility(
                    isSectionEducationVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandEducation);
        });

//        binding.btnExpandEducation.setOnClickListener(v -> {
//            if (!isSectionEducationVisible) {
//                binding.contentEducation.setVisibility(VISIBLE);
//            } else {
//                binding.contentEducation.setVisibility(GONE);
//            }
//        });

        binding.btnExpandExperience.setOnClickListener(v -> {
            isSectionExperienceVisible = !isSectionExperienceVisible;
            binding.contentExperience.setVisibility(
                    isSectionExperienceVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandExperience);
        });

        binding.btnExpandProjects.setOnClickListener(v -> {
            isSectionProjectsVisible = !isSectionProjectsVisible;
            binding.contentProjects.setVisibility(
                    isSectionProjectsVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandProjects);
        });


        binding.btnExpandSkills.setOnClickListener(v -> {
            isSectionSkillsVisible = !isSectionSkillsVisible;
            binding.contentSkills.setVisibility(
                    isSectionSkillsVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandSkills);
        });


        binding.btnExpandCertifications.setOnClickListener(v -> {
            isSectionCertificationVisible = !isSectionCertificationVisible;
            binding.contentCertifications.setVisibility(
                    isSectionCertificationVisible ? VISIBLE : View.GONE
            );
            setViewsVisibility(binding.btnExpandCertifications);
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

        binding.btnSave.setOnClickListener(v -> {
            for (Education e : educationList){
                db.educationDao().insertEducation(e);
            }
        });

    }
    private void setFirstView() {
        binding.contentPersonal.setVisibility(VISIBLE);
        binding.contentSummary.setVisibility(GONE);
        binding.contentEducation.setVisibility(GONE);
        binding.contentExperience.setVisibility(GONE);
        binding.contentProjects.setVisibility(GONE);
        binding.contentSkills.setVisibility(GONE);
        binding.contentCertifications.setVisibility(GONE);
    }
    private void setViewsVisibility(ImageButton btn) {
        if (btn == binding.btnExpandPersonal){
            binding.contentPersonal.setVisibility(VISIBLE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandSummary){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(VISIBLE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandEducation){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(VISIBLE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandExperience){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(VISIBLE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandProjects){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(VISIBLE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandSkills){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(VISIBLE);
            binding.contentCertifications.setVisibility(GONE);
        }
        else if (btn == binding.btnExpandCertifications){
            binding.contentPersonal.setVisibility(GONE);
            binding.contentSummary.setVisibility(GONE);
            binding.contentEducation.setVisibility(GONE);
            binding.contentExperience.setVisibility(GONE);
            binding.contentProjects.setVisibility(GONE);
            binding.contentSkills.setVisibility(GONE);
            binding.contentCertifications.setVisibility(VISIBLE);
        }

    }

//    private void loadEducationData() {
//        // Load or initialize your education data
//        educationList.clear();
//        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
//        educationList.add(new Education("MIT", "BSc Computer Science", "Cambridge, MA", YearMonth.of(2020,3), YearMonth.of(2024,3)));
//    }

//    private void loadExperienceData() {
//        experienceList.clear();
//        experienceList.add(new Experience("Software Engineer", "San Francisco, CA", YearMonth.of(2020,3), YearMonth.of(2023,6), "Google", "Worked on developing scalable backend services."));
//        experienceList.add(new Experience("Senior Developer", "New York, NY", YearMonth.of(2020,3), YearMonth.of(2023,6), "Facebook", "Leading a team on mobile app development."));
//    }
//
//    private void loadProjectData() {
//        projectList.clear();
//        projectList.add(new Project("AI Chatbot Integration", "OpenAI", YearMonth.of(2021, 5), YearMonth.of(2022, 12), "Developed and integrated an AI-powered chatbot into customer support systems to automate query handling."));
//        projectList.add(new Project("E-Commerce Web Platform", "Amazon", YearMonth.of(2019, 8), YearMonth.of(2021, 3), "Built a scalable web-based e-commerce platform with payment gateway integration and advanced product recommendation system."));
//    }

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