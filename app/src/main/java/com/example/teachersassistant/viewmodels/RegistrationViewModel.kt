package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.common.RegistrationOrLoginResult
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.models.repositories.user.IRegisterUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUserRepository: IRegisterUserRepository
) : ViewModel() {
    val username = MutableLiveData<String>()
    val rawPassword = MutableLiveData<String>()
    val confirmRawPassword = MutableLiveData<String>()

    private val _registrationState = MutableStateFlow(RegistrationOrLoginResult.NONE)
    val registrationState: StateFlow<RegistrationOrLoginResult> = _registrationState


    fun register() {
        val userDto = RegisterOrLoginUserDto(
            username = username.value!!.trim(),
            rawPassword = rawPassword.value!!.trim()
        )

        viewModelScope.launch {
            val registeredUser = registerUserRepository.registerUser(userDto)

            if (registeredUser == null) {
                _registrationState.value = RegistrationOrLoginResult.FAILED
            }
            else {
                _registrationState.value = RegistrationOrLoginResult.SUCCESS
            }
        }
    }
}