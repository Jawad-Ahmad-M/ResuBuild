package com.resumebuilder.app.itemClasses;

public class Experience {
    private String jobRole, location, startDate, endDate, companyName, jobDescription;
    public Experience(String jobRole, String location, String startDate, String endDate, String companyName, String jobDescription) {
        this.jobRole = jobRole;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

}
