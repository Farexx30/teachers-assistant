package com.example.teachersassistant.models.repositories

interface StudentDao {
    fun getStudentId(): Int? {
        return 5
    }

    fun getStudentName(): String {
        return "Name"
    }
}