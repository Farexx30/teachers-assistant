package com.example.teachersassistant.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subject.SubjectStudentDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AssignStudentsToSubjectViewModel @Inject constructor(
    private val studentRepository: IStudentRepository,
    private val subjectRepository: ISubjectRepository,
    private val userContext: IUserContext
) : ViewModel() {
    private var counter = 0

    private val _selectedStudentsCounter = MutableLiveData("Selected students: $counter")
    val selectedStudentsCounter: LiveData<String> = _selectedStudentsCounter

    private val _students = MutableStateFlow<List<StudentDto>>(emptyList())
    val students: StateFlow<List<StudentDto>> = _students


    fun updateSelectedStudentsCounter(count: Int) {
        counter = count
        _selectedStudentsCounter.value = "Selected students: $counter"
    }

    fun getNotSubjectStudentsBySubjectId(subjectId: Long) {
        viewModelScope.launch {
            val studentsDtos = studentRepository.getNotSubjectStudentsBySubjectId(subjectId, userContext.getCurrentUserId()!!)
            _students.value = studentsDtos
        }
    }

    fun assignStudentsToSubject(studentsIdsToAssign: List<Long>, subjectId: Long) {
        val newSubjectStudentsDtos = studentsIdsToAssign.map { studentId ->
            SubjectStudentDto(
                subjectId = subjectId,
                studentId = studentId)
        }

        viewModelScope.launch {
            subjectRepository.assignStudentToSubject(newSubjectStudentsDtos)
        }
    }
}