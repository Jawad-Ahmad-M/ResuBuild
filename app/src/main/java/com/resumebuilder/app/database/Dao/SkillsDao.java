package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Skill;

import java.util.List;

@Dao
public interface SkillsDao {
    @Insert
    void insertSkill(Skill skill);
    @Update
    void updateSkill(Skill skill);
    @Delete
    void deleteSkill(Skill skill);
    @Query("SELECT * FROM Skill")
    List<Skill> getAllSkills();
}
