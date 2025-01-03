package com.example.teachersassistant.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectStudentInfoViewModel @Inject constructor(
    private val studentRepository: IStudentRepository
) : ViewModel() {
    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _albumNumber = MutableLiveData<String>()
    val albumNumber: LiveData<String> = _albumNumber

    private val _grades = MutableStateFlow<List<SubjectStudentGradeDto>>(emptyList())
    val grades: StateFlow<List<SubjectStudentGradeDto>> = _grades


    fun getSubjectStudentWithGrades(subjectId: Long, studentId: Long) {
        viewModelScope.launch {
            val (studentDto, gradesDtos) = studentRepository.getSubjectStudentWithGrades(subjectId, studentId)

            _firstName.postValue(studentDto.firstName)
            _lastName.postValue(studentDto.lastName)
            _albumNumber.postValue(studentDto.albumNumber)

            _grades.value = gradesDtos
        }
    }
}