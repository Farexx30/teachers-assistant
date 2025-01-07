package com.example.teachersassistant.viewmodels.subject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.constants.Day
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.usercontext.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val subjectRepository: ISubjectRepository
) : ViewModel() {
    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> = _currentDay

    private val _subjects = MutableStateFlow<List<SubjectAndHoursDto>>(emptyList())
    val subjects: StateFlow<List<SubjectAndHoursDto>> = _subjects

    fun getSubjectsByDay(dayName: String) {
        viewModelScope.launch {
            val day = Day.fromString(dayName)
            val subjectsWithHoursDtos = subjectRepository.getSubjectsWithHours(day, userContext.getCurrentUserId()!!)

            _currentDay.postValue(dayName)
            _subjects.value = subjectsWithHoursDtos
        }
    }
}