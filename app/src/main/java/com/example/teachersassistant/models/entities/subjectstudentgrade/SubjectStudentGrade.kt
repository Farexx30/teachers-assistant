package com.example.teachersassistant.models.entities.subjectstudentgrade

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.subject.Subject

@Entity(
    tableName = DatabaseConstants.TableNames.SUBJECT_STUDENT_GRADE,
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
    var subjectId: Long,
    var studentId: Long,
    val gradeTitle: String,
    val grade: Float,
    val gradeComment: String?
)
