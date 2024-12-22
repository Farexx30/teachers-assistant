package com.example.teachersassistant.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.TeachersAssistantDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InitialViewModel @Inject constructor(
    private val database: TeachersAssistantDatabase
) : ViewModel() {
    fun test() {
        val a = 1
        viewModelScope.launch {
            val students = database.studentDao().getAll() // TEST
            println("Students: $students")
        }
    }
}