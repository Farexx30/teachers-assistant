package com.example.teachersassistant.models.repositories.user

import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto

interface IResetAllUserDataRepository {
    abstract suspend fun resetAllUserData(currentUserId: Long)
}