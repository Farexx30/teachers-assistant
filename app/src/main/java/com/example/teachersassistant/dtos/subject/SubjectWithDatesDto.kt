package com.example.teachersassistant.dtos.subject

import androidx.room.Embedded
import com.example.teachersassistant.common.Day
import java.time.LocalTime

data class SubjectWithDatesDto(
    val subject: SubjectBasicInfoDto,
    val dates: List<SubjectDateDto>
)
