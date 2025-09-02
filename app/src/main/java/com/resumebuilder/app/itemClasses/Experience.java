package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Experience")
public class Experience {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String jobRole, location, companyName, jobDescription;
    private String startDate, endDate;
    public Experience() {}
    public Experience(String jobRole, String location,String startDate, String endDate, String companyName, String jobDescription) {
        this.jobRole = jobRole;
        this.location = location;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @NonNull
    @Override
    public String toString() {
        return "JobExperience{" +
                "jobRole='" + jobRole + '\'' +
                ", location='" + location + '\'' +
                ", companyName='" + companyName + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", startDate=" + (startDate != null ? startDate : "null") +
                ", endDate=" + (endDate != null ? endDate : "null") +
                '}';
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getLocation() {
        return location;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
