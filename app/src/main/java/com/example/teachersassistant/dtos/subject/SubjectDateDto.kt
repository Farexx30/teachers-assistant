package com.example.teachersassistant.dtos.subject

import com.example.teachersassistant.common.Day
import java.time.LocalTime

data class SubjectDateDto(
    val id: Long,
    val day: Day,
    val startHour: LocalTime,
    val endHour: LocalTime
)
