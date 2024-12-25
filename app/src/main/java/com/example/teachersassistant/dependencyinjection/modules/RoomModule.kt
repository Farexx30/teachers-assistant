package com.example.teachersassistant.dependencyinjection.modules

import android.content.Context
import androidx.room.Room
import com.example.teachersassistant.models.room.TeachersAssistantDatabase
import com.example.teachersassistant.models.daos.StudentDao
import com.example.teachersassistant.models.daos.SubjectDao
import com.example.teachersassistant.models.daos.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Here we define how to create our ROOM specific dependencies:
@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideTeachersAssistantDatabase(@ApplicationContext context: Context): TeachersAssistantDatabase {
        return Room.databaseBuilder(context, TeachersAssistantDatabase::class.java, "TeachersAssistant.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: TeachersAssistantDatabase) : UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideSubjectDao(database: TeachersAssistantDatabase) : SubjectDao {
        return database.subjectDao()
    }

    @Provides
    @Singleton
    fun provideStudentDao(database: TeachersAssistantDatabase) : StudentDao {
        return database.studentDao()
    }
}