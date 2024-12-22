package com.example.teachersassistant.di

import com.example.teachersassistant.models.repositories.StudentDao
import com.example.teachersassistant.models.repositories.StudentDaoImpl
import com.example.teachersassistant.viewmodels.CurrentUserSharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//Here we define how to create our dependencies:
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStudentDao() : StudentDao {
        return StudentDaoImpl()
    }
}