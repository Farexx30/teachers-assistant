package com.example.teachersassistant.models.entities.student

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.models.entities.subject.Subject

@Entity(
    tableName = DatabaseTableName.SUBJECT_STUDENT_GRADE,
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
    indices = [Index(value = ["id"], unique = true)]
)
data class SubjectStudentGrade(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val subjectId: Long,
    val studentId: Long,
    val gradeTitle: String,
    val grade: Float,
    val gradeComment: String?
)
