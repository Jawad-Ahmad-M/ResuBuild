package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Skill")
public class Skill {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nameOfSkill;

    public Skill() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
