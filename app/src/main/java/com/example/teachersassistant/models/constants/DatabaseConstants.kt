package com.example.teachersassistant.models.constants

object DatabaseConstants {
    const val DATABASE_NAME = "TeachersAssistant.db"

    object TableNames {
        const val USERS: String = "Users"
        const val SUBJECTS: String = "Subjects"
        const val STUDENTS: String = "Students"
        const val SUBJECT_STUDENT: String = "SubjectStudent"
        const val SUBJECT_DATE: String = "SubjectDate"
        const val SUBJECT_STUDENT_GRADE: String = "SubjectStudentGrade"
    }
}