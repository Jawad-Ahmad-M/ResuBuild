package com.resumebuilder.app.itemClasses;

public class Certification {
    private String nameOfCertification, nameOfOrganization, issuedDate, link;

    public Certification(String nameOfCertification, String nameOfOrganization, String issuedDate) {
        this.nameOfCertification = nameOfCertification;
        this.nameOfOrganization = nameOfOrganization;
        this.issuedDate = issuedDate;
    }

    public Certification() {
    }

    public Certification(String nameOfCertification, String nameOfOrganization, String issuedDate, String link) {
        this.nameOfCertification = nameOfCertification;
        this.nameOfOrganization = nameOfOrganization;
        this.issuedDate = issuedDate;
        this.link = link;
    }

    public String getNameOfCertification() {
        return nameOfCertification;
    }


    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public String getLink() {
        return link;
    }

}
