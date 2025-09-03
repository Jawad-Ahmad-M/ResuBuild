package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(
        tableName = "Education",
        foreignKeys = @ForeignKey(
                entity = PersonalDetails.class,
                parentColumns = "resumeId",
                childColumns = "resumeId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("resumeId")}
)
public class Education {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int resumeId;

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String universityName, location, titleOfDegree;

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public Education(int resumeId) {
        this.resumeId = resumeId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setTitleOfDegree(String titleOfDegree) {
        this.titleOfDegree = titleOfDegree;
    }

    private String startDate, endDate;

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

    public Education(String universityName, String location, String titleOfDegree, String  startDate, String  endDate) {
        this.universityName = universityName;
        this.location = location;
        this.titleOfDegree = titleOfDegree;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Education(int id, int resumeId, String universityName, String location, String titleOfDegree, String startDate, String endDate) {
        this.id = id;
        this.resumeId = resumeId;
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

    public String getTitleOfDegree() {
        return titleOfDegree;
    }

}
