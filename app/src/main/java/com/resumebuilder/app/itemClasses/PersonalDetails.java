package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ResumeDetails")
public class PersonalDetails {
    @PrimaryKey(autoGenerate = true)
    private int resumeId;
    private String name, location, personalEmail, phoneNumber, linkedInLink, githubLink;
    private String summary;

    public PersonalDetails() {
    }


    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPersonalEmail() {
        return personalEmail;
    }

    public void setPersonalEmail(String personalEmail) {
        this.personalEmail = personalEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLinkedInLink() {
        return linkedInLink;
    }

    public void setLinkedInLink(String linkedInLink) {
        this.linkedInLink = linkedInLink;
    }

    public String getGithubLink() {
        return githubLink;
    }

    public void setGithubLink(String githubLink) {
        this.githubLink = githubLink;
    }

    @NonNull
    @Override
    public String toString() {
        return "Resume{" +
                "resumeId=" + resumeId +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", linkedInLink='" + linkedInLink + '\'' +
                ", githubLink='" + githubLink + '\'' +
                ", summary='" + summary + '\'' +
                '}';
    }
}
