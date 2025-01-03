package com.example.teachersassistant.dtos.student

data class SubjectStudentGradeDto(
    val id: Long,
    val title: String,
    val grade: Float,
    val comment: String?
)
