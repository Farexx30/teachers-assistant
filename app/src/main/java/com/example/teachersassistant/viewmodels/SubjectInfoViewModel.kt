package com.example.teachersassistant.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.IUserContext
import com.example.teachersassistant.session.UserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubjectInfoViewModel @Inject constructor(
    private val subjectRepository: ISubjectRepository,
    private val userContext: IUserContext
): ViewModel() {

    val subjectName = MutableLiveData<String>()

    private val _subjectId = MutableStateFlow(0L)
    val subjectId: StateFlow<Long> = _subjectId

    fun getSubjectData(subjectId: Long) {
        viewModelScope.launch {
            val subjectDto = subjectRepository.getSubjectBasicInfoById(subjectId)
            subjectName.postValue(subjectDto.name)
            _subjectId.value = subjectDto.id
        }
    }

    fun saveNewSubject() {
        val newSubjectDto = SubjectBasicInfoDto(
            id = 0,
            name = subjectName.value!!.trim()
        )

        viewModelScope.launch {
            val insertedSubjectId = subjectRepository.insertSubject(newSubjectDto, userContext.getCurrentUserId()!!)
            _subjectId.value = insertedSubjectId
        }
    }

    fun updateSubjectName(subjectId: Long) {
        val updatedSubjectDto = SubjectBasicInfoDto(
            id = subjectId,
            name = subjectName.value!!.trim()
        )

        viewModelScope.launch {
            subjectRepository.updateSubject(updatedSubjectDto, userContext.getCurrentUserId()!!)
        }
    }
}