package com.example.teachersassistant.models.entities.subject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.constants.Day
import java.time.LocalTime

@Entity(tableName = DatabaseConstants.TableNames.SUBJECT_DATE, foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
],
    indices = [Index(value = ["id"], unique = true)]
)
data class SubjectDate(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var subjectId: Long,
    val day: Day,
    val startHour: LocalTime,
    val endHour: LocalTime
)
