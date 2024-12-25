package com.example.teachersassistant.models.entities.subject

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.models.entities.user.User

@Entity(tableName = DatabaseTableName.SUBJECTS, foreignKeys = [
    ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["teacherId"],
        onDelete = ForeignKey.CASCADE
    ),
],
    indices = [Index(value = ["id"], unique = true)]
)
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    var teacherId: Long
)
