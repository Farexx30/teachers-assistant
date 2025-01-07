package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.models.entities.subjectstudent.SubjectStudent

@Dao
interface SubjectStudentDao {
    //!!! INSERTS !!!//

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun assignStudentsToSubject(newSubjectStudents: List<SubjectStudent>)


    //!!! DELETIONS !!!//
    @Delete
    suspend fun removeStudentFromSubject(subjectStudentToRemove: SubjectStudent)


    //!!! QUERIES !!!//

    //Query for fetching current subject students:
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.albumNumber
        FROM ${DatabaseConstants.TableNames.STUDENTS} s
        JOIN ${DatabaseConstants.TableNames.SUBJECT_STUDENT} ss ON ss.studentId = s.id
        WHERE ss.subjectId = :subjectId 
        ORDER BY s.lastName COLLATE NOCASE, s.firstName COLLATE NOCASE, s.albumNumber
        """)
    suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>

    //Query for fetching students that are NOT in current subject:
    @Query("""
        SELECT id, firstName, lastName, albumNumber
        FROM ${DatabaseConstants.TableNames.STUDENTS}
        WHERE teacherId = :teacherId 
            AND id NOT IN (SELECT s.id 
                           FROM ${DatabaseConstants.TableNames.STUDENTS} s
                           JOIN ${DatabaseConstants.TableNames.SUBJECT_STUDENT} ss ON ss.studentId = s.id
                           WHERE ss.subjectId = :subjectId)
        ORDER BY lastName COLLATE NOCASE, firstName COLLATE NOCASE, albumNumber
        """)
    suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long, teacherId: Long): List<StudentDto>

}