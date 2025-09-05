package com.resumebuilder.app.itemClasses;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "Resume")
public class Resume {
    @PrimaryKey(autoGenerate = true)
    private int resumeId;
    private String name, timestamp;
    public Resume() {}

    public Resume(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "Resume{" +
                "ResumeId='" + resumeId + '\'' +
                ",Resume Name=  = " + name + '\'' +
                ",TimeStamp = " + timestamp +
                '}';
    }
}
