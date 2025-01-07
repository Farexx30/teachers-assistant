package com.example.teachersassistant.models.entities.subjectstudent

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.subject.Subject

@Entity(
    tableName = DatabaseConstants.TableNames.SUBJECT_STUDENT,
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
    val subjectId: Long,
    val studentId: Long
)
