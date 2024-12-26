package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.models.repositories.subject.ISubjectRepository
import com.example.teachersassistant.session.IUserContext
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
    private val _subjects = MutableStateFlow<List<SubjectBasicInfoDto>>(emptyList())
    val subjects: StateFlow<List<SubjectBasicInfoDto>> = _subjects
    init {
        viewModelScope.launch {
            val subjectsDtos = subjectRepository.getAllCurrentUserSubjects(userContext.getCurrentUserId()!!)
            _subjects.value = subjectsDtos
        }
    }
}