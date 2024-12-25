package com.example.teachersassistant.dtos.student

data class StudentWithGradesDto(
    val student: StudentDto,
    val grades: List<SubjectStudentGradeDto>
)
