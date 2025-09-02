package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Experience;

import java.util.List;

@Dao
public interface ExperienceDao {
    @Insert
    void insertExperience(Experience experience);
    @Update
    void updateExperience(Experience experience);
    @Delete
    void deleteExperience(Experience experience);
    @Query("SELECT * FROM Experience")
    List<Experience> getAllExperience();
}
