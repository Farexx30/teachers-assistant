package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.common.RegistrationOrLoginResult
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.models.repositories.user.ILoginUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserRepository: ILoginUserRepository
) : ViewModel() {
    val username = MutableLiveData<String>()
    val rawPassword = MutableLiveData<String>()

    private val _loginState = MutableStateFlow(RegistrationOrLoginResult.NONE)
    val loginState: StateFlow<RegistrationOrLoginResult> = _loginState


    fun login() {
        val userDto = RegisterOrLoginUserDto(
            username = username.value!!.trim(),
            rawPassword = rawPassword.value!!.trim()
        )

        viewModelScope.launch {
            val loggedInUser = loginUserRepository.loginUser(userDto)

            if (loggedInUser == null) {
                _loginState.value = RegistrationOrLoginResult.FAILED
            }
            else {
                _loginState.value = RegistrationOrLoginResult.SUCCESS
            }
        }
    }
}