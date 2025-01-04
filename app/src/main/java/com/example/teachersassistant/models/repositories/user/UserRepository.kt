package com.example.teachersassistant.models.repositories.user

import com.example.teachersassistant.common.mapToUser
import com.example.teachersassistant.common.mapToUserBasicInfoDto
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto
import com.example.teachersassistant.models.daos.UserDao
import org.mindrot.jbcrypt.BCrypt
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
): IRegisterUserRepository, ILoginUserRepository, IResetAllUserDataRepository {
    override suspend fun registerUser(newUserDto: RegisterOrLoginUserDto): UserBasicInfoDto? {
        val isUsernameInUse = userDao.isUsernameInUse(newUserDto.username)
        if (isUsernameInUse) {
            return null
        }

        val newUser = newUserDto.mapToUser() //Map to User
        val newUserId = userDao.insertUser(newUser) //Insert into database and get generated id
        val newUserWithId = newUser.copy(id = newUserId) //Populate with generated id
        val newUserBasicInfoDto = newUserWithId.mapToUserBasicInfoDto() //Map to UserBasicInfoDto
        return newUserBasicInfoDto
    }

    override suspend fun loginUser(userDto: RegisterOrLoginUserDto): UserBasicInfoDto? {
        val user = userDao.getUserByUsername(userDto.username)
            ?: return null

        val passwordVerificationResult = BCrypt.checkpw(userDto.rawPassword, user.passwordHash)
        if (!passwordVerificationResult) {
            return null
        }

        val userBasicInfoDto = user.mapToUserBasicInfoDto()
        return userBasicInfoDto
    }

    override suspend fun resetAllUserData(currentUserId: Long) {
        userDao.resetAllUserData(currentUserId)
    }
}