package com.example.teachersassistant.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.teachersassistant.R
import com.example.teachersassistant.TeachersAssistantDatabase
import com.example.teachersassistant.models.repositories.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Here we define how to create our dependencies:
@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {

    @Singleton
    @Provides
    fun provideTeachersAssistantDatabase(@ApplicationContext context: Context): TeachersAssistantDatabase {
        return Room.databaseBuilder(context, TeachersAssistantDatabase::class.java, "TeachersAssistant.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideStudentDao(database: TeachersAssistantDatabase) : StudentDao {
        return database.studentDao()
    }
}