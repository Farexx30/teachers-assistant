package com.example.teachersassistant.models.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.subject.Subject
import com.example.teachersassistant.models.entities.user.User
import com.example.teachersassistant.models.entities.student.SubjectStudent
import com.example.teachersassistant.models.entities.subject.SubjectDate
import com.example.teachersassistant.models.entities.student.SubjectStudentGrade
import com.example.teachersassistant.models.daos.StudentDao
import com.example.teachersassistant.models.daos.SubjectDao
import com.example.teachersassistant.models.daos.UserDao

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
    abstract fun userDao(): UserDao
    abstract fun subjectDao(): SubjectDao
    abstract fun studentDao(): StudentDao
}