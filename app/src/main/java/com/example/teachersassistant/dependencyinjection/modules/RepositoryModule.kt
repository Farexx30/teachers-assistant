package com.example.teachersassistant.dependencyinjection.modules

import com.example.teachersassistant.models.repositories.user.ILoginUserRepository
import com.example.teachersassistant.models.repositories.user.IRegisterUserRepository
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.models.repositories.student.StudentRepository
import com.example.teachersassistant.models.repositories.subject.SubjectRepository
import com.example.teachersassistant.models.repositories.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

//Here we define how to create our repository specific dependencies:
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRegisterUserRepository(userRepository: UserRepository): IRegisterUserRepository

    @Binds
    @Singleton
    abstract fun bindLoginUserRepository(userRepository: UserRepository): ILoginUserRepository

    @Binds
    @Singleton
    abstract fun bindSubjectRepository(subjectRepository: SubjectRepository): ISubjectRepository

    @Binds
    @Singleton
    abstract fun bindStudentRepository(studentRepository: StudentRepository): IStudentRepository
}