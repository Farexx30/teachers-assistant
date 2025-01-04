package com.example.teachersassistant.dependencyinjection.modules

import com.example.teachersassistant.models.repositories.user.IRegisterUserRepository
import com.example.teachersassistant.models.repositories.user.UserRepository
import com.example.teachersassistant.session.IUserContext
import com.example.teachersassistant.session.UserContext
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {
    @Binds
    @Singleton
    abstract fun bindUserContext(userContext: UserContext): IUserContext
}