package com.example.teachersassistant.viewmodels.subjectstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
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

    private val _grades = MutableStateFlow<MutableList<SubjectStudentGradeDto>>(mutableListOf())
    val grades: StateFlow<MutableList<SubjectStudentGradeDto>> = _grades


    fun getSubjectStudentWithGrades(subjectId: Long, studentId: Long) {
        viewModelScope.launch {
            val (studentDto, gradesDtos) = studentRepository.getSubjectStudentWithGrades(subjectId, studentId)

            _firstName.postValue(studentDto.firstName)
            _lastName.postValue(studentDto.lastName)
            _albumNumber.postValue(studentDto.albumNumber)

            _grades.value = gradesDtos.toMutableList()
        }
    }

    suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto) {
        studentRepository.deleteGrade(gradeToDeleteDto)
        _grades.value.remove(gradeToDeleteDto)
    }
}