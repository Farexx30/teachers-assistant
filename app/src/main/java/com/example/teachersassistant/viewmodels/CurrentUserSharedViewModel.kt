package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import com.example.teachersassistant.models.repositories.StudentDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrentUserSharedViewModel @Inject constructor(
    private val studentDao: StudentDao
): ViewModel() {

    val id: Int? = studentDao.getStudentId()
    val name: String = studentDao.getStudentName()
}