package com.example.teachersassistant.models.repositories

import androidx.room.Dao
import androidx.room.Query
import com.example.teachersassistant.TeachersAssistantDatabase
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.models.entities.Student
import javax.inject.Inject

@Dao
interface StudentDao {
    @Query("SELECT * FROM ${DatabaseTableName.STUDENTS}")
    suspend fun getAll(): List<Student>
}