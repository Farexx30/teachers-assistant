package com.example.teachersassistant

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teachersassistant.models.Converters
import com.example.teachersassistant.models.entities.Student
import com.example.teachersassistant.models.entities.Subject
import com.example.teachersassistant.models.entities.User
import com.example.teachersassistant.models.entities.SubjectStudent
import com.example.teachersassistant.models.entities.SubjectDate
import com.example.teachersassistant.models.entities.SubjectStudentGrade
import com.example.teachersassistant.models.repositories.StudentDao

@Database(entities = [
    User::class,
    Subject::class,
    Student::class,
    SubjectStudent::class,
    SubjectDate::class,
    SubjectStudentGrade::class
    ],
    version = 1,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class TeachersAssistantDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao
}