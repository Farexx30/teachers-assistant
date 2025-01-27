package com.example.teachersassistant.viewmodels.subjectstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.models.repositories.subjectstudent.ISubjectStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectStudentsViewModel @Inject constructor(
    private val subjectRepository: ISubjectRepository,
    private val subjectStudentRepository: ISubjectStudentRepository
) : ViewModel() {
    private val _subjectName = MutableLiveData<String>()
    val subjectName: LiveData<String> = _subjectName

    private val _students = MutableStateFlow<MutableList<StudentDto>>(mutableListOf())
    val students: StateFlow<MutableList<StudentDto>> = _students


    fun getSubjectStudentsBySubjectId(subjectId: Long) {
        viewModelScope.launch {
            val subjectInfoDto = subjectRepository.getSubjectBasicInfoById(subjectId)
            val studentsDtos = subjectStudentRepository.getSubjectStudentsBySubjectId(subjectId)

            _subjectName.postValue(subjectInfoDto.name)
            _students.value = studentsDtos.toMutableList()
        }
    }

    suspend fun removeStudentFromSubject(studentToRemoveFromSubjectDto: StudentDto, subjectId: Long) {
        val subjectStudentToRemoveDto = SubjectStudentDto(
            subjectId = subjectId,
            studentId = studentToRemoveFromSubjectDto.id
        )

        subjectStudentRepository.removeStudentFromSubject(subjectStudentToRemoveDto)
        _students.value.remove(studentToRemoveFromSubjectDto)
    }
}