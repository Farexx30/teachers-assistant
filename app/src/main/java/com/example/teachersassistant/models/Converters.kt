package com.example.teachersassistant.models

import androidx.room.TypeConverter
import com.example.teachersassistant.common.Day
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun fromDay(day: Day): String {
        return day.name
    }

    @TypeConverter
    fun toDay(value: String): Day {
        return Day.valueOf(value)
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String {
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME)
    }

    @TypeConverter
    fun toLocalTime(value: String): LocalTime {
        return LocalTime.parse(value, DateTimeFormatter.ISO_LOCAL_TIME)
    }
}