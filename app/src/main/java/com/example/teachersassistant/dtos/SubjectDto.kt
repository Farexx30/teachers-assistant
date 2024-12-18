package com.example.teachersassistant.dtos

import com.example.teachersassistant.common.Day
import java.time.LocalTime

data class SubjectDto(
    val id: Int,
    val name: String,
    val day: Day,
    val startHour: LocalTime,
    val endHour: LocalTime
)
