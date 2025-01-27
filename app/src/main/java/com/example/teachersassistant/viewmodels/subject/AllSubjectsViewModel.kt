package com.example.teachersassistant.viewmodels.subject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.usercontext.IUserContext
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSubjectsViewModel @Inject constructor(
    private val userContext: IUserContext,
    private val subjectRepository: ISubjectRepository
) : ViewModel() {
    private val _subjects = MutableStateFlow<MutableList<SubjectBasicInfoDto>>(mutableListOf())
    val subjects: StateFlow<MutableList<SubjectBasicInfoDto>> = _subjects
    init {
        viewModelScope.launch {
            val subjectsDtos = subjectRepository.getAllCurrentUserSubjects(userContext.getCurrentUserId()!!)
            _subjects.value = subjectsDtos.toMutableList()
        }
    }

    suspend fun deleteSubject(subjectToDeleteDto: SubjectBasicInfoDto) {
        subjectRepository.deleteSubject(subjectToDeleteDto)
        _subjects.value.remove(subjectToDeleteDto)
    }
}