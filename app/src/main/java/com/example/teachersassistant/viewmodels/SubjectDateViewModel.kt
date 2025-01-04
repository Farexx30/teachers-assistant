package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.IUserContext
import com.example.teachersassistant.session.UserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class SubjectDateViewModel @Inject constructor(
    private val subjectRepository: ISubjectRepository
) : ViewModel() {
    val day = MutableLiveData<String>()
    val startHour = MutableLiveData<String>()
    val startMinute = MutableLiveData<String>()
    val endHour = MutableLiveData<String>()
    val endMinute = MutableLiveData<String>()

    fun getSubjectDate(dateId: Long) {
        viewModelScope.launch {
            val subjectDateDto = subjectRepository.getSubjectDateById(dateId)

            day.postValue(subjectDateDto.day.asString)
            startHour.postValue(subjectDateDto.startHour.hour.toString())
            startMinute.postValue(subjectDateDto.startHour.minute.toString())
            endHour.postValue(subjectDateDto.endHour.hour.toString())
            endMinute.postValue(subjectDateDto.endHour.minute.toString())
        }
    }

    suspend fun saveSubjectDate(subjectId: Long, dateId: Long) {
        val subjectDateDto = SubjectDateDto(
            id = dateId,
            day = Day.fromString(day.value!!.trim()),
            startHour = LocalTime.of(startHour.value!!.trim().toInt(), startMinute.value!!.trim().toInt()),
            endHour = LocalTime.of(endHour.value!!.trim().toInt(), endMinute.value!!.trim().toInt()),
        )

        if (dateId == 0L) {
            subjectRepository.insertSubjectDate(subjectDateDto, subjectId)
        }
        else {
            subjectRepository.updateSubjectDate(subjectDateDto, subjectId)
        }
    }
}