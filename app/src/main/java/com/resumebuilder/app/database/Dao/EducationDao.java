package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Education;

import java.util.List;

@Dao
public interface EducationDao {
    @Insert
    void insertEducation(Education education);
    @Update
    void updateEducation(Education education);
    @Delete
    void deleteEducation(Education education);
    @Query("SELECT * FROM Education ORDER BY id;")
    List<Education> getAllEducation();
}