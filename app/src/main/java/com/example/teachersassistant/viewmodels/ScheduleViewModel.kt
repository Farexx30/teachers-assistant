package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val subjectRepository: ISubjectRepository
) : ViewModel() {

    private val _subjects = MutableStateFlow<List<SubjectAndHoursDto>>(emptyList())
    val subjects: StateFlow<List<SubjectAndHoursDto>> = _subjects

    init {
        viewModelScope.launch {
            val currentDay = LocalDate.now().dayOfWeek.name
            getSubjectsByDay(currentDay)
        }
    }

    fun getSubjectsByDay(dayAsString: String) {
        viewModelScope.launch {
            val day = Day.fromString(dayAsString)
            val subjectsWithHoursDtos = subjectRepository.getSubjectsWithHours(day, userContext.getCurrentUserId()!!)

            _subjects.value = subjectsWithHoursDtos
        }
    }
}