package com.resumebuilder.app.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.resumebuilder.app.database.Dao.CertificationDao;
import com.resumebuilder.app.database.Dao.EducationDao;
import com.resumebuilder.app.database.Dao.ExperienceDao;
import com.resumebuilder.app.database.Dao.PersonalDetailsDao;
import com.resumebuilder.app.database.Dao.ProjectDao;
import com.resumebuilder.app.database.Dao.ResumeDao;
import com.resumebuilder.app.database.Dao.SkillsDao;
import com.resumebuilder.app.itemClasses.Certification;
import com.resumebuilder.app.itemClasses.Education;
import com.resumebuilder.app.itemClasses.Experience;
import com.resumebuilder.app.itemClasses.PersonalDetails;
import com.resumebuilder.app.itemClasses.Project;
import com.resumebuilder.app.itemClasses.Resume;
import com.resumebuilder.app.itemClasses.Skill;

@Database(entities = {Education.class, Experience.class, Project.class, Certification.class, Skill.class, PersonalDetails.class, Resume.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract ResumeDao resumeDao();
    public abstract PersonalDetailsDao personalDetailsDao();
    public abstract EducationDao educationDao();
    public abstract ExperienceDao experienceDao();
    public abstract ProjectDao projectDao();
    public abstract SkillsDao skillsDao();
    public abstract CertificationDao certificationDao();
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized(AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "resume_builder_db"
                            ).build();
                }
            }
        }
        return INSTANCE;
    }

}
