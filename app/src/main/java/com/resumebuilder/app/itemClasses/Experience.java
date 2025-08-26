package com.resumebuilder.app.itemClasses;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Experience {
    private String jobRole, location, companyName, jobDescription;
    private YearMonth startDate, endDate;
    public Experience() {}

    public Experience(String jobRole, String location,YearMonth startDate, YearMonth endDate, String companyName, String jobDescription) {
        this.jobRole = jobRole;
        this.location = location;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getJobRole() {
        return jobRole;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
