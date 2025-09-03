package com.resumebuilder.app.database.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.resumebuilder.app.itemClasses.Certification;

import java.util.List;

@Dao
public interface CertificationDao {
    @Insert
    void insertCertification(Certification certification);
    @Update
    void updateCertification(Certification certification);
    @Delete
    void deleteCertification(Certification certification);
    @Query("SELECT * FROM Certification WHERE resumeId =:resumeId")
    List<Certification> getAllCertificationsById(int resumeId);
}
