package com.example.teachersassistant.viewmodels.user

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.constants.RegistrationOrLoginResult
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.models.repositories.user.IRegisterUserRepository
import com.example.teachersassistant.session.usercontext.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val registerUserRepository: IRegisterUserRepository
) : ViewModel() {
    val username = MutableLiveData<String>()
    val rawPassword = MutableLiveData<String>()
    val confirmRawPassword = MutableLiveData<String>()

    private val _registrationState = MutableStateFlow(RegistrationOrLoginResult.NONE)
    val registrationState: StateFlow<RegistrationOrLoginResult> = _registrationState

    val isRegisterButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(username) { checkFields() }
        addSource(rawPassword) { checkFields() }
        addSource(confirmRawPassword) { checkFields() }
    }


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
                userContext.login(registeredUser)
                _registrationState.value = RegistrationOrLoginResult.SUCCESS
            }

            _registrationState.value = RegistrationOrLoginResult.NONE
        }
    }

    private fun checkFields() {
        isRegisterButtonEnabled.value = canRegister()
    }

    private fun canRegister(): Boolean {
        return listOf(username.value, rawPassword.value, confirmRawPassword.value)
            .all { it?.trim()?.isNotEmpty() == true }
                && rawPassword.value!!.trim() == confirmRawPassword.value!!.trim()
    }
}