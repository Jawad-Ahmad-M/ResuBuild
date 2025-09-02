package com.resumebuilder.app.itemClasses;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Certification")
public class Certification {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nameOfCertification, nameOfOrganization, issuedDate, link;
    public Certification() {
    }

    public Certification(String nameOfCertification, String nameOfOrganization, String issuedDate, String link) {
        this.nameOfCertification = nameOfCertification;
        this.nameOfOrganization = nameOfOrganization;
        this.issuedDate = issuedDate;
        this.link = link;
    }

    public Certification(String nameOfCertification, String nameOfOrganization, String issuedDate) {
        this.nameOfCertification = nameOfCertification;
        this.nameOfOrganization = nameOfOrganization;
        this.issuedDate = issuedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameOfCertification(String nameOfCertification) {
        this.nameOfCertification = nameOfCertification;
    }

    public void setNameOfOrganization(String nameOfOrganization) {
        this.nameOfOrganization = nameOfOrganization;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setLink(String link) {
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
