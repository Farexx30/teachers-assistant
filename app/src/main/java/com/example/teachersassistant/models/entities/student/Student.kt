package com.example.teachersassistant.models.entities.student

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.models.entities.user.User

@Entity(tableName = DatabaseTableName.STUDENTS, foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["teacherId"],
        onDelete = ForeignKey.CASCADE
    )
],
    indices = [Index(value = ["id"], unique = true)]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val albumNumber: String,
    var teacherId: Long
)
