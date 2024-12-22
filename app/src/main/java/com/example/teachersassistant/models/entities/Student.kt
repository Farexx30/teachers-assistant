package com.example.teachersassistant.models.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName

@Entity(tableName = DatabaseTableName.STUDENTS, foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )
],
    indices = [Index(value = ["id"], unique = true)]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val albumNumber: String,
    val userId: Int
)
