package com.example.teachersassistant.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teachersassistant.models.repositories.user.IResetAllUserDataRepository
import com.example.teachersassistant.session.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainMenuViewModel @Inject constructor(
    private val userRepository: IResetAllUserDataRepository,
    private val userContext: IUserContext
) : ViewModel() {
    private val _currentUserUsername = MutableLiveData("Welcome: ${userContext.getCurrentUserUsername()}")
    val currentUserUsername: LiveData<String?> = _currentUserUsername

    //BE CAREFUL: DELETES ALL CURRENT USER DATA!!!
    suspend fun resetAllData() {
        userRepository.resetAllUserData(userContext.getCurrentUserId()!!)
    }

    fun logout() {
        userContext.logout()
    }
}