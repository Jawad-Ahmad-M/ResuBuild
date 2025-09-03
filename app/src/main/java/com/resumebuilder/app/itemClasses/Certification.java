package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Certification",
        foreignKeys = @ForeignKey(
                entity = PersonalDetails.class,
                parentColumns = "resumeId",
                childColumns = "resumeId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("resumeId")}
)
public class Certification {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int resumeId;
    private String nameOfCertification, nameOfOrganization, issuedDate, link;
    public Certification() {
    }

    public Certification(int resumeId) {
        this.resumeId = resumeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
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

    @NonNull
    @Override
    public String toString() {
        return "Certification{" +
                "name='" + getNameOfCertification() + '\'' +
                ", organization='" + getNameOfOrganization() + '\'' +
                ", Issued Date='" + (getIssuedDate() != null ? getIssuedDate() : "null") + '\'' +
                ", Link='" + (getLink() != null ? getLink() : "null") + '\'' +
                '}';
    }
}
