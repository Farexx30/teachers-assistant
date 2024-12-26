package com.example.teachersassistant.session

import com.example.teachersassistant.dtos.user.UserBasicInfoDto
import javax.inject.Inject

class UserContext @Inject constructor() : IUserContext {
    private var currentUser: UserBasicInfoDto? = null

    override fun getCurrentUserId(): Long? = currentUser?.id
    override fun getCurrentUserUsername(): String? = currentUser?.username

    override fun login(loggedInUserDto: UserBasicInfoDto) {
        currentUser = loggedInUserDto
    }

    override fun logout() {
        currentUser = null
    }
}