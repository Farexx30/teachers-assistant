package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class SubjectDateViewModel @Inject constructor(
    private val subjectRepository: ISubjectRepository
) : ViewModel() {
    val day = MutableLiveData(1)
    val startHour = MutableLiveData(0)
    val startMinute = MutableLiveData(0)
    val endHour = MutableLiveData(0)
    val endMinute = MutableLiveData(0)

    fun getSubjectDate(dateId: Long) {
        viewModelScope.launch {
            val subjectDateDto = subjectRepository.getSubjectDateById(dateId)

            day.postValue(subjectDateDto.day.value)
            startHour.postValue(subjectDateDto.startHour.hour)
            startMinute.postValue(subjectDateDto.startHour.minute)
            endHour.postValue(subjectDateDto.endHour.hour)
            endMinute.postValue(subjectDateDto.endHour.minute)
        }
    }

    suspend fun saveSubjectDate(subjectId: Long, dateId: Long) {
        val subjectDateDto = SubjectDateDto(
            id = dateId,
            day = Day.fromValue(day.value!!),
            startHour = LocalTime.of(startHour.value!!, startMinute.value!!),
            endHour = LocalTime.of(endHour.value!!, endMinute.value!!),
        )

        if (dateId == 0L) {
            subjectRepository.insertSubjectDate(subjectDateDto, subjectId)
        }
        else {
            subjectRepository.updateSubjectDate(subjectDateDto, subjectId)
        }
    }
}