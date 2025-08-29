package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Education {
    private String universityName, location, titleOfDegree;
    private YearMonth startDate, endDate;

    @NonNull
    public String toString(){
        return "Education{" +
                "University='" + universityName + '\'' +
                ",Degree='" + titleOfDegree + '\'' +
                ",Range='" + startDate + "/" +endDate + '\'' +
                ",'location='" + location + '\'' +
                '}';
    }

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


    public String getLocation() {
        return location;
    }

    public String getStartDate() {
        if (startDate != null){
            return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        } else {
            return  "";
        }
    }

    public String getEndDate() {
        if (endDate != null) {
            return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
        } else {
            return "";
        }
    }

    public String getTitleOfDegree() {
        return titleOfDegree;
    }

}
