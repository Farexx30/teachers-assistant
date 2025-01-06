package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MediatorLiveData
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
    val grade = MutableLiveData(3)
    val comment = MutableLiveData<String?>()

    val isSaveGradeButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(title) { checkFields() }
    }

    fun getGradeById(gradeId: Long) {
        viewModelScope.launch {
            val gradeDto = studentRepository.getGradeById(gradeId)

            title.postValue(gradeDto.title)
            grade.postValue(mapGradeToNumberPickerValue(gradeDto.grade)) //Because NumberPicker cannot hold Float data and we map it from Float to Int
            comment.postValue(gradeDto.comment)
        }
    }

    suspend fun saveGrade(gradeId: Long, subjectId: Long, studentId: Long) {
        val gradeDto = SubjectStudentGradeDto(
            id = gradeId,
            title = title.value!!.trim(),
            grade = mapNumberPickerValueToGrade(grade.value!!),
            comment = comment.value?.trim()
        )

        if (gradeId == 0L) {
            studentRepository.insertGrade(gradeDto, subjectId, studentId)
        }
        else {
            studentRepository.updateGrade(gradeDto, subjectId, studentId)
        }
    }

    private fun checkFields() {
        isSaveGradeButtonEnabled.value = canSaveGrade()
    }

    private fun canSaveGrade(): Boolean {
        return title.value?.trim()?.isNotEmpty() == true
    }

    private fun mapGradeToNumberPickerValue(grade: Float): Int {
        return when(grade) {
            2.0F -> 0
            3.0F -> 1
            3.5F -> 2
            4.0F -> 3
            4.5F -> 4
            5.0F -> 5
            else -> null!! //Not possible
        }
    }

    private fun mapNumberPickerValueToGrade(value: Int): Float {
        return when(value) {
            0 -> 2.0F
            1 -> 3.0F
            2 -> 3.5F
            3 -> 4.0F
            4 -> 4.5F
            5 -> 5.0F
            else -> null!! //Not possible
        }
    }
}