package com.resumebuilder.app.itemClasses;

public class Education {
    private String universityName, location, titleOfDegree, startDate, endDate;

    public Education(String universityName, String location, String titleOfDegree, String startDate, String endDate) {
        this.universityName = universityName;
        this.location = location;
        this.titleOfDegree = titleOfDegree;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitleOfDegree() {
        return titleOfDegree;
    }

    public void setTitleOfDegree(String titleOfDegree) {
        this.titleOfDegree = titleOfDegree;
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
}
