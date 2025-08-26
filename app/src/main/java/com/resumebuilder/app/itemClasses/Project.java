package com.resumebuilder.app.itemClasses;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Project {
    private String titleOfProject, organization;
    private YearMonth  startDate, endDate;

    public Project() {
    }
    private  String projectDesc;

    public Project(String titleOfProject, String organization, YearMonth startDate, YearMonth endDate, String projectDesc) {
        this.titleOfProject = titleOfProject;
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectDesc = projectDesc;
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

    public String getProjectDesc() {
        return projectDesc;
    }

    public String getTitleOfProject() {
        return titleOfProject;
    }

    public String getOrganization() {
        return organization;
    }
}
