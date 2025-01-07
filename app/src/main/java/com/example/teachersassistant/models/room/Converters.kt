package com.example.teachersassistant.models.room

import androidx.room.TypeConverter
import com.example.teachersassistant.constants.Day
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Converters {
    @TypeConverter
    fun fromDay(day: Day): Int {
        return day.value
    }

    @TypeConverter
    fun toDay(value: Int): Day {
        return Day.fromValue(value)
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