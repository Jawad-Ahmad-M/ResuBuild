package com.resumebuilder.app.sync;

import android.content.Context;
import android.widget.Toast;

import com.resumebuilder.app.database.AppDatabase;
import com.resumebuilder.app.itemClasses.Certification;
import com.resumebuilder.app.itemClasses.Education;
import com.resumebuilder.app.itemClasses.Experience;
import com.resumebuilder.app.itemClasses.Project;
import com.resumebuilder.app.itemClasses.Skill;
import com.resumebuilder.app.utils.SyncUtil;

import java.util.List;
import java.util.Objects;

public class DataSyncManager {
    private final AppDatabase db;
    private final Context context;

    public DataSyncManager(Context context) {
        this.context = context;
        this.db = AppDatabase.getInstance(context.getApplicationContext());
    }
    public void syncEducation(List<Education> uiList, int resumeId) {
        List<Education> dbList = db.educationDao().getAllEducationById(resumeId);

        SyncUtil.sync(
                dbList,
                uiList,
                Education::getId,
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getUniversityName(), newItem.getUniversityName()) &&
                                Objects.equals(oldItem.getTitleOfDegree(), newItem.getTitleOfDegree()) &&
                                Objects.equals(oldItem.getLocation(), newItem.getLocation()) &&
                                Objects.equals(oldItem.getStartDate(), newItem.getStartDate()) &&
                                Objects.equals(oldItem.getEndDate(), newItem.getEndDate()),
                new SyncUtil.DaoOps<>() {
                    @Override
                    public void insert(Education item) {
                        db.educationDao().insertEducation(item);
                    }

                    @Override
                    public void update(Education item) {
                        db.educationDao().updateEducation(item);
                    }

                    @Override
                    public void delete(Education item) {
                        db.educationDao().deleteEducation(item);
                    }
                }
        );
        showToast("Education synced.");
    }

    public void syncExperience(List<Experience> uiList, int resumeId) {
        List<Experience> dbList = db.experienceDao().getAllExperienceById(resumeId);

        SyncUtil.sync(
                dbList,
                uiList,
                Experience::getId,
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getJobRole(), newItem.getJobRole()) &&
                                Objects.equals(oldItem.getCompanyName(), newItem.getCompanyName()) &&
                                Objects.equals(oldItem.getLocation(), newItem.getLocation()) &&
                                Objects.equals(oldItem.getStartDate(), newItem.getStartDate()) &&
                                Objects.equals(oldItem.getEndDate(), newItem.getEndDate()) &&
                                Objects.equals(oldItem.getJobDescription(), newItem.getJobDescription()),
                new SyncUtil.DaoOps<>() {
                    @Override
                    public void insert(Experience item) {
                        db.experienceDao().insertExperience(item);
                    }

                    @Override
                    public void update(Experience item) {
                        db.experienceDao().updateExperience(item);
                    }

                    @Override
                    public void delete(Experience item) {
                        db.experienceDao().deleteExperience(item);
                    }
                }
        );
        showToast("Experience synced.");
    }

    public void syncProject(List<Project> uiList, int resumeId) {
        List<Project> dbList = db.projectDao().getAllProjectsById(resumeId);

        SyncUtil.sync(
                dbList,
                uiList,
                Project::getId,
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getTitleOfProject(), newItem.getTitleOfProject()) &&
                                Objects.equals(oldItem.getOrganization(), newItem.getOrganization()) &&
                                Objects.equals(oldItem.getStartDate(), newItem.getStartDate()) &&
                                Objects.equals(oldItem.getEndDate(), newItem.getEndDate()) &&
                                Objects.equals(oldItem.getProjectDesc(), newItem.getProjectDesc()),
                new SyncUtil.DaoOps<>() {
                    public void insert(Project item) {
                        db.projectDao().insertProject(item);
                    }

                    public void update(Project item) {
                        db.projectDao().updateProject(item);
                    }

                    public void delete(Project item) {
                        db.projectDao().deleteProject(item);
                    }
                }
        );
        showToast("Projects synced.");
    }
    public void syncSkill(List<Skill> uiList, int resumeId) {
        List<Skill> dbList = db.skillsDao().getAllSkillsById(resumeId);

        SyncUtil.sync(
                dbList,
                uiList,
                Skill::getId,
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getNameOfSkill(), newItem.getNameOfSkill()),
                new SyncUtil.DaoOps<>() {
                    public void insert(Skill item) {
                        db.skillsDao().insertSkill(item);
                    }

                    public void update(Skill item) {
                        db.skillsDao().updateSkill(item);
                    }

                    public void delete(Skill item) {
                        db.skillsDao().deleteSkill(item);
                    }
                }
        );

        showToast("Skills synced.");
    }

    public void syncCertifications(List<Certification> uiList, int resumeId) {
        List<Certification> dbList = db.certificationDao().getAllCertificationsById(resumeId);

        SyncUtil.sync(
                dbList,
                uiList,
                Certification::getId,
                (oldItem, newItem) ->
                        Objects.equals(oldItem.getNameOfCertification(), newItem.getNameOfCertification()) &&
                                Objects.equals(oldItem.getLink(), newItem.getLink()) &&
                                Objects.equals(oldItem.getIssuedDate(), newItem.getIssuedDate()) &&
                                Objects.equals(oldItem.getNameOfOrganization(), newItem.getNameOfOrganization()),
                new SyncUtil.DaoOps<>() {
                    public void insert(Certification item) {
                        db.certificationDao().insertCertification(item);
                    }

                    public void update(Certification item) {
                        db.certificationDao().updateCertification(item);
                    }

                    public void delete(Certification item) {
                        db.certificationDao().deleteCertification(item);
                    }
                }
        );
        showToast("Certifications synced.");
    }

    private void showToast(String message) {
        android.os.Handler handler = new android.os.Handler(context.getMainLooper());
        handler.post(() ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        );
    }
}
