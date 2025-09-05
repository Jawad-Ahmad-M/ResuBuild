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
import com.resumebuilder.app.itemClasses.PersonalDetails;
import com.resumebuilder.app.itemClasses.Project;
import com.resumebuilder.app.itemClasses.Skill;
import com.resumebuilder.app.sync.DataSyncManager;
import com.resumebuilder.app.testActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailsFragment extends Fragment {
    private final String DEBUG_TAG = "Room DB Services";

    private AppDatabase db;
    private DataSyncManager dataSyncManager;
    private ExecutorService executorService;
    private boolean isSectionPersonalVisible = true;
    private boolean isSectionSummaryVisible  = true;
    private boolean isSectionEducationVisible  = true;
    private boolean isSectionExperienceVisible  = true;
    private boolean isSectionProjectsVisible  = true;
    private boolean isSectionSkillsVisible  = true;
    private boolean isSectionCertificationVisible = true;

    private FragmentDetailsBinding binding;
    private final List<Education> educationList = new ArrayList<>();
    private final List<Experience> experienceList = new ArrayList<>();
    private final List<Project> projectList = new ArrayList<>();
    private final List<Skill> skillList = new ArrayList<>();
    private final List<Certification> certificationList = new ArrayList<>();
    private PersonalDetails details;
    private int currentResumeId;
    private EducationAdapter educationAdapter;
    private ExperienceAdapter experienceAdapter;
    private ProjectAdapter projectAdapter;
    private SkillAdapter skillAdapter;
    private CertificationAdapter certificationAdapter;

    public DetailsFragment() {
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

        executorService = Executors.newFixedThreadPool(5);
        db = AppDatabase.getInstance(requireContext().getApplicationContext());
        dataSyncManager = new DataSyncManager(requireContext().getApplicationContext());

//        executorService.execute(()->{
//            List<PersonalDetails> personalDetailsList = db.personalDetailsDao().getAllPersonalDetails();
//            if (personalDetailsList != null && !personalDetailsList.isEmpty()) {
//                details = personalDetailsList.get(0);
//            } else {
//                details = new PersonalDetails();
//                long id = db.personalDetailsDao().insertPersonalDetails(details);
//                details.setResumeId((int) id);
//            }
//            Log.d(DEBUG_TAG, details.toString());
//            requireActivity().runOnUiThread(() -> {
//                binding.etName.setText(details.getName() != null ? details.getName() : "");
//                binding.etLocation.setText(details.getLocation() != null ? details.getLocation() : "");
//                binding.etContact.setText(details.getPhoneNumber() != null ? details.getPhoneNumber() : "");
//                binding.etEmail.setText(details.getPersonalEmail() != null ? details.getPersonalEmail() : "");
//                binding.etSummary.setText(details.getSummary() != null ? details.getSummary() : "");
//                binding.etLinkedin.setText(details.getLinkedInLink() != null ? details.getLinkedInLink() : "");
//                binding.etGithub.setText(details.getGithubLink() != null ? details.getGithubLink() : "");
//                currentResumeId = details.getResumeId();
//            });
//        });



        setFirstView();
        loadingDataFromDB();
        adapterSettings();

        binding.btnGetView.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), testActivity.class);
            intent.putExtra("pdf_assets", "RenderCV_sb2nov_Theme.pdf");
            startActivity(intent);
        });

        expandButtons();
        additionButtons();

        binding.btnSave.setOnClickListener(v -> saveData());

    }
    private void expandButtons() {
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
    }
    private void additionButtons() {
        binding.btnAddEducation.setOnClickListener(v -> {
            educationList.add(new Education(currentResumeId));
            educationAdapter.notifyItemInserted(educationList.size() - 1);
        });

        binding.btnAddExperience.setOnClickListener(v -> {
            experienceList.add(new Experience(currentResumeId));
            experienceAdapter.notifyItemInserted(experienceList.size() - 1);
        });

        binding.btnAddProjects.setOnClickListener(v -> {
            projectList.add(new Project(currentResumeId));
            projectAdapter.notifyItemInserted(projectList.size() - 1);
        });

        binding.btnAddSkill.setOnClickListener(v -> {
            skillList.add(new Skill(currentResumeId));
            skillAdapter.notifyItemInserted(skillList.size() - 1);
        });

        binding.btnAddCertification.setOnClickListener(v -> {
            certificationList.add(new Certification(currentResumeId));
            certificationAdapter.notifyItemInserted(certificationList.size() - 1);
        });
    }
    private void loadingDataFromDB() {
        executorService.execute(() -> {
//            db.educationDao().insertEducation(new Education("ABC", "abc","XYZ","Jul 2028","Jun 2027"));
            List<Education> list = db.educationDao().getAllEducationById(currentResumeId);
            educationList.addAll(list);
            Log.d(DEBUG_TAG, educationList.toString());
        });

        executorService.execute(()->{
            List<Experience> list = db.experienceDao().getAllExperienceById(currentResumeId);
            experienceList.addAll(list);
            Log.d(DEBUG_TAG, experienceList.toString());
        });

        executorService.execute(()->{
            List<Project> list = db.projectDao().getAllProjectsById(currentResumeId);
            projectList.addAll(list);
            Log.d(DEBUG_TAG, projectList.toString());
        });

        executorService.execute(()->{
            List<Skill> list = db.skillsDao().getAllSkillsById(currentResumeId);
            skillList.addAll(list);
            Log.d(DEBUG_TAG, skillList.toString());
        });

        executorService.execute(()->{
            List<Certification> list = db.certificationDao().getAllCertificationsById(currentResumeId);
            certificationList.addAll(list);
            Log.d(DEBUG_TAG, certificationList.toString());
        });
    }
    private void saveData() {

        educationAdapter.updateEducationListFromInputs();
        experienceAdapter.updateExperienceListFromInputs();
        projectAdapter.updateProjectListFromInputs();
        skillAdapter.updateSkillListFromInputs();
        certificationAdapter.updateCertificationListFromInputs();

        executorService.execute(() -> {
            details.setName(binding.etName.getText().toString());
            details.setLocation(binding.etLocation.getText().toString());
            details.setPhoneNumber(binding.etContact.getText().toString());
            details.setPersonalEmail(binding.etEmail.getText().toString());
            details.setSummary(binding.etSummary.getText().toString());
            details.setLinkedInLink(binding.etLinkedin.getText().toString());
            details.setGithubLink(binding.etGithub.getText().toString());

            db.runInTransaction(() -> {
                db.personalDetailsDao().insertOrUpdate(details);

                dataSyncManager.syncEducation(educationList,currentResumeId);
                dataSyncManager.syncExperience(experienceList,currentResumeId);
                dataSyncManager.syncProject(projectList,currentResumeId);
                dataSyncManager.syncSkill(skillList,currentResumeId);
                dataSyncManager.syncCertifications(certificationList,currentResumeId);
            });

            Log.d(DEBUG_TAG, "All Entries Added");

            Log.d(DEBUG_TAG, educationList.toString());
            Log.d(DEBUG_TAG, experienceList.toString());
            Log.d(DEBUG_TAG, projectList.toString());
            Log.d(DEBUG_TAG, skillList.toString());
            Log.d(DEBUG_TAG, certificationList.toString());
        });
    }
    private void adapterSettings() {
        educationAdapter = new EducationAdapter(educationList, requireContext());
        experienceAdapter = new ExperienceAdapter(experienceList, requireContext());
        projectAdapter = new ProjectAdapter(projectList, requireContext());
        skillAdapter = new SkillAdapter(skillList);
        certificationAdapter = new CertificationAdapter(certificationList);


        binding.rvEducation.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvEducation.setAdapter(educationAdapter);
        educationAdapter.setRecyclerView(binding.rvEducation);

        binding.rvExperience.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvExperience.setAdapter(experienceAdapter);
        experienceAdapter.setRecyclerView(binding.rvExperience);

        binding.rvProjects.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProjects.setAdapter(projectAdapter);
        projectAdapter.setRecyclerView(binding.rvProjects);

        binding.rvSkills.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSkills.setAdapter(skillAdapter);
        skillAdapter.setRecyclerView(binding.rvSkills);

        binding.rvCertifications.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvCertifications.setAdapter(certificationAdapter);
        certificationAdapter.setRecyclerView(binding.rvCertifications);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}