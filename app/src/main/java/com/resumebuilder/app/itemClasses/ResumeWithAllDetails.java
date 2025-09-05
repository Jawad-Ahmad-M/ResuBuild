package com.resumebuilder.app.itemClasses;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ResumeWithAllDetails {
    @Embedded
    public PersonalDetails personalDetails;
    @Relation(
            parentColumn = "resumeId",
            entityColumn = "resumeId"
    )
    public List<Education> educationList;

    @Relation(
            parentColumn = "resumeId",
            entityColumn = "resumeId"
    )
    public List<Experience> experienceList;

    @Relation(
            parentColumn = "resumeId",
            entityColumn = "resumeId"
    )
    public List<Project> projectList;

    @Relation(
            parentColumn = "resumeId",
            entityColumn = "resumeId"
    )
    public List<Skill> skillList;

    @Relation(
            parentColumn = "resumeId",
            entityColumn = "resumeId"
    )
    public List<Certification> certificationList;
}
