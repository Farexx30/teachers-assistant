package com.example.teachersassistant.viewmodels.subjectstudent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.models.repositories.student.IStudentRepository
import com.example.teachersassistant.models.repositories.subjectstudentgrade.ISubjectStudentGradeRepository
import com.example.teachersassistant.models.repositories.subjectstudentgrade.SubjectStudentGradeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

@HiltViewModel
class SubjectStudentInfoViewModel @Inject constructor(
    private val studentRepository: IStudentRepository,
    private val subjectStudentGradeRepository: ISubjectStudentGradeRepository
) : ViewModel() {
    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String> = _firstName

    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String> = _lastName

    private val _albumNumber = MutableLiveData<String>()
    val albumNumber: LiveData<String> = _albumNumber

    private val _averageGrade = MutableLiveData<String>()
    val averageGrade: LiveData<String> = _averageGrade

    private val _grades = MutableStateFlow<MutableList<SubjectStudentGradeDto>>(mutableListOf())
    val grades: StateFlow<MutableList<SubjectStudentGradeDto>> = _grades


    fun getSubjectStudentWithGrades(subjectId: Long, studentId: Long) {
        viewModelScope.launch {
            val studentDto = studentRepository.getStudentDataById(studentId)
            val gradesDtos = subjectStudentGradeRepository.getSubjectStudentGrades(subjectId, studentId)

            _firstName.postValue(studentDto.firstName)
            _lastName.postValue(studentDto.lastName)
            _albumNumber.postValue(studentDto.albumNumber)
            _grades.value = gradesDtos.toMutableList()
            calculateAndUpdateAverageGrade()
        }
    }

    suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto) {
        subjectStudentGradeRepository.deleteGrade(gradeToDeleteDto)
        _grades.value.remove(gradeToDeleteDto)
    }

    fun calculateAndUpdateAverageGrade() {
        val gradesValue = _grades.value
        if (gradesValue.isNotEmpty()) {
            val averageGrade = gradesValue.map { it.grade }.average()
            val formattedAndRoundedAverageGrade = BigDecimal(averageGrade.toString())
                .setScale(2, RoundingMode.HALF_UP)
                .toString()

            _averageGrade.postValue(formattedAndRoundedAverageGrade)
        } else {
            _averageGrade.postValue("0.00") //If student have no grades in current subject.
        }
    }
}