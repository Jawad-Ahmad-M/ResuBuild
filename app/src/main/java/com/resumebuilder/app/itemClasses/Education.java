package com.resumebuilder.app.itemClasses;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Education {
    private String universityName, location, titleOfDegree;
    private YearMonth startDate, endDate;

    public Education() {}

    public Education(String universityName, String location, String titleOfDegree, YearMonth startDate, YearMonth endDate) {
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

    public String getStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    public void setStartDate(YearMonth startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    public void setEndDate(YearMonth endDate) {
        this.endDate = endDate;
    }

    public String getTitleOfDegree() {
        return titleOfDegree;
    }

    public void setTitleOfDegree(String titleOfDegree) {
        this.titleOfDegree = titleOfDegree;
    }

}
