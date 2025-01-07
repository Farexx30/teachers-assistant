package com.example.teachersassistant.viewmodels.user

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.constants.RegistrationOrLoginResult
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.models.repositories.user.ILoginUserRepository
import com.example.teachersassistant.session.usercontext.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val loginUserRepository: ILoginUserRepository
) : ViewModel() {
    val username = MutableLiveData<String>()
    val rawPassword = MutableLiveData<String>()

    val isLoginButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(username) { checkFields() }
        addSource(rawPassword) { checkFields() }
    }

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
                userContext.login(loggedInUser)
                _loginState.value = RegistrationOrLoginResult.SUCCESS
            }

            _loginState.value = RegistrationOrLoginResult.NONE
        }
    }

    private fun checkFields() {
        isLoginButtonEnabled.value = canLogin()
    }

    private fun canLogin(): Boolean {
        return listOf(username.value, rawPassword.value)
            .all { it?.trim()?.isNotEmpty() == true }
    }
}