package com.example.teachersassistant.session.usercontext

import com.example.teachersassistant.dtos.user.UserBasicInfoDto

interface IUserContext {
    abstract fun getCurrentUserId(): Long?
    abstract fun getCurrentUserUsername(): String?
    abstract fun login(loggedInUserDto: UserBasicInfoDto)
    abstract fun logout()
}