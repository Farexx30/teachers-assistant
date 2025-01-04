package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GradeViewModel @Inject constructor(
    private val studentRepository: IStudentRepository
): ViewModel() {
    val title = MutableLiveData<String>()
    val grade = MutableLiveData<String>()
    val comment = MutableLiveData<String?>()

    fun getGradeById(gradeId: Long) {
        viewModelScope.launch {
            val gradeDto = studentRepository.getGradeById(gradeId)

            title.postValue(gradeDto.title)
            grade.postValue(gradeDto.grade.toString())
            comment.postValue(gradeDto.comment)
        }
    }

    suspend fun saveGrade(gradeId: Long, subjectId: Long, studentId: Long) {
        val gradeDto = SubjectStudentGradeDto(
            id = gradeId,
            title = title.value!!.trim(),
            grade = grade.value!!.toFloat(),
            comment = comment.value?.trim()
        )

        if (gradeId == 0L) {
            studentRepository.insertGrade(gradeDto, subjectId, studentId)
        }
        else {
            studentRepository.updateGrade(gradeDto, subjectId, studentId)
        }
    }
}