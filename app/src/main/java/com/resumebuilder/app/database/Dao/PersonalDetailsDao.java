package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.PersonalDetails;
import com.resumebuilder.app.itemClasses.ResumeWithAllDetails;

import java.util.List;

@Dao
public interface PersonalDetailsDao {
    // Insert a new PersonalDetails entry
    @Insert
    long insertPersonalDetails(PersonalDetails personalDetails);

    // Update an existing PersonalDetails entry
    @Update
    void updatePersonalDetails(PersonalDetails personalDetails);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdate(PersonalDetails details);

    // Delete a PersonalDetails entry
    @Delete
    void deletePersonalDetails(PersonalDetails personalDetails);

    @Query("DELETE FROM ResumeDetails")
    void deleteAllPersonalDetails();

    // Get a PersonalDetails by resumeId
    @Query("SELECT * FROM ResumeDetails WHERE resumeId = :resumeId")
    PersonalDetails getPersonalDetailsById(int resumeId);

    // Get all PersonalDetails entries (all resumes)
    @Query("SELECT * FROM ResumeDetails")
    List<PersonalDetails> getAllPersonalDetails();

    // Get the full resume with all related data (Education, Experience, etc.)
    @Transaction
    @Query("SELECT * FROM ResumeDetails WHERE resumeId = :resumeId")
    ResumeWithAllDetails getFullResume(int resumeId);
}
