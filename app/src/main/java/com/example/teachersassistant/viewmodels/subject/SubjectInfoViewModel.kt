package com.example.teachersassistant.viewmodels.subject

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.usercontext.IUserContext
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

    val isSaveSubjectButtonEnabled = MediatorLiveData<Boolean>().apply {
        addSource(subjectName) { checkFields() }
    }

    private val _subjectDates = MutableStateFlow<MutableList<SubjectDateDto>>(mutableListOf())
    val subjectDates: StateFlow<MutableList<SubjectDateDto>> = _subjectDates

    private val _subjectId = MutableStateFlow(0L)
    val subjectId: StateFlow<Long> = _subjectId


    fun getSubjectData(subjectId: Long) {
        viewModelScope.launch {
            val (subjectDto, subjectDatesDtos) = subjectRepository.getSubjectWithDates(subjectId)

            subjectName.postValue(subjectDto.name)
            _subjectId.value = subjectDto.id
            _subjectDates.value = subjectDatesDtos.toMutableList()
        }
    }

    suspend fun saveNewSubject() {
        val newSubjectDto = SubjectBasicInfoDto(
            id = 0L,
            name = subjectName.value!!.trim()
        )

        val insertedSubjectId = subjectRepository.insertSubject(newSubjectDto, userContext.getCurrentUserId()!!)
        _subjectId.value = insertedSubjectId
    }

    suspend fun updateSubjectName(subjectId: Long) {
        val updatedSubjectDto = SubjectBasicInfoDto(
            id = subjectId,
            name = subjectName.value!!.trim()
        )

        subjectRepository.updateSubject(updatedSubjectDto, userContext.getCurrentUserId()!!)
    }

    suspend fun deleteSubjectDate(subjectDateToDeleteDto: SubjectDateDto) {
        subjectRepository.deleteSubjectDate(subjectDateToDeleteDto)
        _subjectDates.value.remove(subjectDateToDeleteDto)
    }

    private fun checkFields() {
        isSaveSubjectButtonEnabled.value = canSaveSubject()
    }

    private fun canSaveSubject(): Boolean {
        return subjectName.value?.trim()?.isNotEmpty() == true
    }
}