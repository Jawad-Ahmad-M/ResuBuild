package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(
        tableName = "Skill",
        foreignKeys = @ForeignKey(
                entity = Resume.class,
                parentColumns = "resumeId",
                childColumns = "resumeId",
                onDelete = ForeignKey.CASCADE
        ),
        indices = {@Index("resumeId")}
)
public class Skill {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int resumeId;
    private String nameOfSkill;

    public Skill() {}

    public Skill(int resumeId) {
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

    public void setNameOfSkill(String nameOfSkill) {
        this.nameOfSkill = nameOfSkill;
    }

    public String getNameOfSkill() {
        return nameOfSkill;
    }

    @NonNull
    @Override
    public String toString() {
        return "Skill{" +
                "skillName='" + (getNameOfSkill() != null ? getNameOfSkill() : "null") + '\'' +
                '}';
    }
}
