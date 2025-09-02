package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.YearMonth;
@Entity(tableName = "Project")
public class Project {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String titleOfProject, organization;
    private String  startDate, endDate;
    private  String projectDesc;

    public Project() {
    }

    public Project(String titleOfProject, String organization, String startDate, String endDate, String projectDesc) {
        this.titleOfProject = titleOfProject;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDesc = projectDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTitleOfProject(String titleOfProject) {
        this.titleOfProject = titleOfProject;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public String getTitleOfProject() {
        return titleOfProject;
    }

    public String getOrganization() {
        return organization;
    }

    @NonNull
    @Override
    public String toString() {
        return "Project{" +
                "titleOfProject='" + titleOfProject + '\'' +
                ", organization='" + organization + '\'' +
                ", startDate=" + (startDate != null ? startDate.toString() : "null") +
                ", endDate=" + (endDate != null ? endDate.toString() : "null") +
                '}';
    }
}
