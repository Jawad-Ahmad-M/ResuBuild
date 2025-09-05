package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Resume;

import java.util.List;

@Dao
public interface ResumeDao {
    @Insert
    long insertResume(Resume resume);
    @Update
    void updateResume(Resume resume);
    @Delete
    void deleteResume(Resume resume);
    @Query("SELECT * FROM Resume")
    List<Resume> getAllResume();
}
