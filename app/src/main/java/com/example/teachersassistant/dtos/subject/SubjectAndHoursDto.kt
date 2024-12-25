package com.example.teachersassistant.dtos.subject

import java.time.LocalTime

data class SubjectAndHoursDto(
    val subjectId: Long,
    val subjectName: String,
    val subjectStartHour: LocalTime,
    val subjectEndHour: LocalTime,
)
