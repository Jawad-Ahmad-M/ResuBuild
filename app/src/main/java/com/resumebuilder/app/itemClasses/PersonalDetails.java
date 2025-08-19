package com.resumebuilder.app.itemClasses;

public class PersonalDetails {
    private String name, location, personalEmail, phoneNumber, linkedInLink, githubLink;

    public PersonalDetails(String name, String location, String personalEmail, String phoneNumber, String linkedInLink) {
        this.name = name;
        this.location = location;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.linkedInLink = linkedInLink;
    }

    public PersonalDetails(String name, String location, String personalEmail, String phoneNumber) {
        this.name = name;
        this.location = location;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
    }

    public PersonalDetails(String name, String location, String personalEmail, String phoneNumber, String linkedInLink, String githubLink) {
        this.name = name;
        this.location = location;
        this.personalEmail = personalEmail;
        this.phoneNumber = phoneNumber;
        this.linkedInLink = linkedInLink;
        this.githubLink = githubLink;
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
}
