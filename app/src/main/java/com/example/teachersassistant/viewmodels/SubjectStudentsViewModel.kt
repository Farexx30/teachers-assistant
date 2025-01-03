package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectStudentsViewModel @Inject constructor(
    private val studentRepository: IStudentRepository
) : ViewModel() {
    private val _students = MutableStateFlow<List<StudentDto>>(emptyList())
    val students: StateFlow<List<StudentDto>> = _students


    fun getSubjectStudentsBySubjectId(subjectId: Long) {
        viewModelScope.launch {
            val studentsDtos = studentRepository.getSubjectStudentsBySubjectId(subjectId)
            _students.value = studentsDtos
        }
    }
}