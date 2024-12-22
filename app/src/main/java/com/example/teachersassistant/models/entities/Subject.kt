package com.example.teachersassistant.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.common.Day
import java.time.LocalTime

@Entity(tableName = DatabaseTableName.SUBJECTS, foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    ),
],
    indices = [Index(value = ["id"], unique = true)]
)
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val day: Day,
    val startHour: LocalTime,
    val endHour: LocalTime,
    val userId: Int
)
