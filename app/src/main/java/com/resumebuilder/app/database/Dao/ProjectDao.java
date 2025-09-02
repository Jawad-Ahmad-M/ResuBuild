package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Project;

import java.util.List;

@Dao
public interface ProjectDao {
    @Insert
    void insertProject(Project project);
    @Delete
    void deleteProject(Project project);
    @Update
    void updateProject(Project project);
    @Query("SELECT * FROM Project")
    List<Project> getAllProjects();
}
