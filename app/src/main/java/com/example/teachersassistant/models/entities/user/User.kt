package com.example.teachersassistant.models.entities.user

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teachersassistant.common.DatabaseTableName

@Entity(
    tableName = DatabaseTableName.USERS,
    indices = [Index(value = ["id"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val username: String,
    val passwordHash: String
)
