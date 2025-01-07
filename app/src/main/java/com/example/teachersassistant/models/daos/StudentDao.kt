package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.subjectstudentgrade.SubjectStudentGrade

@Dao
interface StudentDao {

    //!!! INSERTS !!!//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(newStudent: Student)


    //!!! UPDATES !!!//
    @Update
    suspend fun updateStudent(updatedStudent: Student)


    //!!! DELETIONS !!!//
    @Delete
    suspend fun deleteStudent(studentToDelete: Student)


    //!!! QUERIES !!!//

    //Query for fetching all currently logged in students:
    @Query("""
       SELECT id, firstName, lastName, albumNumber
       FROM ${DatabaseConstants.TableNames.STUDENTS}
       WHERE teacherId = :currentUserId
       ORDER BY lastName COLLATE NOCASE, firstName COLLATE NOCASE, albumNumber
    """)
    suspend fun getAllCurrentUserStudentsById(currentUserId: Long): List<StudentDto>

    //Query for fetching student's data:
    @Query("""
       SELECT id, firstName, lastName, albumNumber
       FROM ${DatabaseConstants.TableNames.STUDENTS}
       WHERE id = :studentId 
       LIMIT 1
    """)
    suspend fun getStudentDataById(studentId: Long): StudentDto



}