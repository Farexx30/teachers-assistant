package com.example.teachersassistant.dtos.student

data class SubjectStudentGradeDto(
    val id: Long,
    val gradeTitle: String,
    val grade: Float,
    val gradeComment: String?
)
