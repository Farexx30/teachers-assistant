package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.models.entities.user.User

@Dao
interface UserDao {
    //!!! INSERTS !!!//

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(newUser: User): Long


    //!!! QUERIES !!!//

    @Query("""
       SELECT 
       EXISTS (SELECT 1
                FROM ${DatabaseTableName.USERS}
                WHERE username = :username COLLATE NOCASE
                LIMIT 1)
    """)
    suspend fun isUsernameInUse(username: String) : Boolean


    @Query("""
        SELECT id, username, passwordHash
        FROM ${DatabaseTableName.USERS}
        WHERE username = :username COLLATE NOCASE
        LIMIT 1
    """)
    suspend fun getUserByUsername(username: String) : User?
}