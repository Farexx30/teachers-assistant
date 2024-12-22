package com.example.teachersassistant.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.teachersassistant.common.DatabaseTableName

@Entity(
    tableName = DatabaseTableName.SUBJECT_STUDENT,
    primaryKeys = ["subjectId", "studentId"],
    foreignKeys = [
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["subjectId", "studentId"], unique = true)]
)
data class SubjectStudent(
    val subjectId: Int,
    val studentId: Int
)
