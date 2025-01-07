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

    //Query for fetching current subject students:
    @Query("""
        SELECT s.id, s.firstName, s.lastName, s.albumNumber
        FROM ${DatabaseConstants.TableNames.STUDENTS} s
        JOIN ${DatabaseConstants.TableNames.SUBJECT_STUDENT} ss ON ss.studentId = s.id
        WHERE ss.subjectId = :subjectId 
        ORDER BY s.lastName COLLATE NOCASE, s.firstName COLLATE NOCASE, s.albumNumber
        """)
    suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>

    //Query for fetching student that are NOT in current subject:
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

    //Query for fetching student's data with its grades:
    @Query("""
       SELECT id id, gradeTitle title, grade grade, gradeComment comment
       FROM ${DatabaseConstants.TableNames.SUBJECT_STUDENT_GRADE}
       WHERE subjectId = :subjectId AND studentId = :studentId
        """)
    suspend fun getSubjectStudentGrades(subjectId:Long, studentId: Long): List<SubjectStudentGradeDto>

    //Query for fetching student's grade data:
    @Query("""
       SELECT id id, gradeTitle title, grade grade, gradeComment comment
       FROM ${DatabaseConstants.TableNames.SUBJECT_STUDENT_GRADE}
       WHERE id = :gradeId
       LIMIT 1
        """)
    suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto
}