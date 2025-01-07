package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.teachersassistant.models.constants.DatabaseConstants
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
                FROM ${DatabaseConstants.TableNames.USERS}
                WHERE username = :username COLLATE NOCASE
                LIMIT 1)
    """)
    suspend fun isUsernameInUse(username: String) : Boolean


    @Query("""
        SELECT id, username, passwordHash
        FROM ${DatabaseConstants.TableNames.USERS}
        WHERE username = :username COLLATE NOCASE
        LIMIT 1
    """)
    suspend fun getUserByUsername(username: String) : User?



    //!!!DELETES ALL USER DATA!!!//
    @Transaction
    suspend fun resetAllUserData(currentUserId: Long) {
        deleteUserSubjects(currentUserId)
        deleteUserStudents(currentUserId)
    }

    @Query("""
        DELETE FROM ${DatabaseConstants.TableNames.SUBJECTS}
        WHERE teacherId = :currentUserId
    """)
    suspend fun deleteUserSubjects(currentUserId: Long)

    @Query("""
        DELETE FROM ${DatabaseConstants.TableNames.STUDENTS}
        WHERE teacherId = :currentUserId
    """)
    suspend fun deleteUserStudents(currentUserId: Long)
}