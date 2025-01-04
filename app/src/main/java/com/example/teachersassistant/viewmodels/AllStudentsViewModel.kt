package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.session.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllStudentsViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val studentRepository: IStudentRepository
) : ViewModel() {
    private val _students = MutableStateFlow<MutableList<StudentDto>>(mutableListOf())
    val students: StateFlow<MutableList<StudentDto>> = _students
    init {
        viewModelScope.launch {
            val studentsDtos = studentRepository.getAllCurrentUserStudents(userContext.getCurrentUserId()!!)
            _students.value = studentsDtos.toMutableList()
        }
    }

    suspend fun deleteStudent(studentToDeleteDto: StudentDto) {
        studentRepository.deleteStudent(studentToDeleteDto)
        _students.value.remove(studentToDeleteDto)
    }
}