package com.example.teachersassistant.models.repositories.user

import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto

interface IRegisterUserRepository {
    abstract suspend fun registerUser(newUserDto: RegisterOrLoginUserDto): UserBasicInfoDto?
}