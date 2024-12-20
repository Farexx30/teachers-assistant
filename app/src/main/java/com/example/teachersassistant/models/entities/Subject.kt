package com.example.teachersassistant.models.entities

import com.example.teachersassistant.common.Day
import java.time.LocalTime


data class Subject (
    val id: Int,
    val name: String,
    val day: Day,
    val startHour: LocalTime,
    val endHour: LocalTime
)
