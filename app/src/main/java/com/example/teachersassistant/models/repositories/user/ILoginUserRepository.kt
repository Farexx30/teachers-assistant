package com.example.teachersassistant.models.repositories.user

import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto

interface ILoginUserRepository {
    abstract suspend fun loginUser(userDto: RegisterOrLoginUserDto): UserBasicInfoDto?
}