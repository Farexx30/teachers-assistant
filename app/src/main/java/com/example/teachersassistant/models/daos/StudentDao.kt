package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.student.SubjectStudentGrade

@Dao
interface StudentDao {

    //!!! INSERTS !!!//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(newStudent: Student)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(newGrade: SubjectStudentGrade)


    //!!! UPDATES !!!//

    @Update
    suspend fun updateStudent(updatedStudent: Student)

    @Update
    suspend fun updateGrade(updatedGrade: SubjectStudentGrade)


    //!!! DELETIONS !!!//

    @Delete
    suspend fun deleteStudent(studentToDelete: Student)

    @Delete
    suspend fun deleteGrade(gradeToDelete: SubjectStudentGrade)


    //!!! QUERIES !!!//

    //Query for fetching all currently logged in students:
    @Query("""
       SELECT id, firstName, lastName, albumNumber
       FROM ${DatabaseTableName.STUDENTS}
       WHERE teacherId = :currentUserId
    """)
    suspend fun getAllCurrentUserStudentsById(currentUserId: Long): List<StudentDto>

    //Query for fetching student's data:
    @Query("""
       SELECT id, firstName, lastName, albumNumber
       FROM ${DatabaseTableName.STUDENTS}
       WHERE id = :studentId 
       LIMIT 1
    """)
    suspend fun getStudentDataById(studentId: Long): StudentDto

    //Query for fetching current subject students:
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.albumNumber
        FROM ${DatabaseTableName.STUDENTS} s
        JOIN ${DatabaseTableName.SUBJECT_STUDENT} ss ON ss.studentId = s.id
        WHERE ss.subjectId = :subjectId 
        """)
    suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>

    //Query for fetching student that are NOT in current subject:
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.albumNumber
        FROM ${DatabaseTableName.STUDENTS} s
        JOIN ${DatabaseTableName.SUBJECT_STUDENT} ss ON ss.studentId = s.id
        WHERE ss.subjectId <> :subjectId 
        """)
    suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>

    //Query for fetching student's data with its grades:
    @Query("""
       SELECT id, gradeTitle, grade, gradeComment
       FROM ${DatabaseTableName.SUBJECT_STUDENT_GRADE}
       WHERE subjectId = :subjectId AND studentId = :studentId
        """)
    suspend fun getSubjectStudentGrades(subjectId:Long, studentId: Long): List<SubjectStudentGradeDto>

    //Query for fetching student's grade data:
    @Query("""
       SELECT id, gradeTitle, grade, gradeComment
       FROM ${DatabaseTableName.SUBJECT_STUDENT_GRADE}
       WHERE id = :gradeId
       LIMIT 1
        """)
    suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto
}