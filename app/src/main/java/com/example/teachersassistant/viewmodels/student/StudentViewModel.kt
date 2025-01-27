package com.example.teachersassistant.viewmodels.student

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.session.usercontext.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val studentRepository: IStudentRepository
) : ViewModel() {
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val albumNumber = MutableLiveData<String>()

    val isSaveStudentButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(firstName) { checkFields() }
        addSource(lastName) { checkFields() }
        addSource(albumNumber) { checkFields() }
    }


    fun getStudentData(studentId: Long) {
        viewModelScope.launch {
            val studentDto = studentRepository.getStudentDataById(studentId)
            firstName.postValue(studentDto.firstName)
            lastName.postValue(studentDto.lastName)
            albumNumber.postValue(studentDto.albumNumber)
        }
    }

    suspend fun saveStudent(studentId: Long) {
        val studentDto = StudentDto(
            id = studentId,
            firstName = firstName.value!!.trim(),
            lastName = lastName.value!!.trim(),
            albumNumber = albumNumber.value!!.trim()
        )

        if (studentId == 0L) {
            studentRepository.insertStudent(studentDto, userContext.getCurrentUserId()!!)
        }
        else {
            studentRepository.updateStudent(studentDto, userContext.getCurrentUserId()!!)
        }
    }

    private fun checkFields() {
        isSaveStudentButtonEnabled.value = canSaveStudent()
    }

    private fun canSaveStudent(): Boolean {
        return listOf(firstName.value, lastName.value, albumNumber.value)
            .all { it?.trim()?.isNotEmpty() == true }
    }
}