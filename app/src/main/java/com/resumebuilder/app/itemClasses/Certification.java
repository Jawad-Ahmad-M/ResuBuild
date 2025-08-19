package com.resumebuilder.app.itemClasses;

public class Certification {
    private String nameOfCertification, nameOfOrganization, issuedDate, link;

    public Certification(String nameOfCertification, String nameOfOrganization, String issuedDate) {
        this.nameOfCertification = nameOfCertification;
        this.nameOfOrganization = nameOfOrganization;
        this.issuedDate = issuedDate;
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

    public void setNameOfCertification(String nameOfCertification) {
        this.nameOfCertification = nameOfCertification;
    }

    public String getNameOfOrganization() {
        return nameOfOrganization;
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
