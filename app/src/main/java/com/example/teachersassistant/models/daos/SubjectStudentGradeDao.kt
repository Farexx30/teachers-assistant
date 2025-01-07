package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.models.entities.subjectstudentgrade.SubjectStudentGrade

@Dao
interface SubjectStudentGradeDao {

    //!!! INSERTS !!!//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGrade(newGrade: SubjectStudentGrade)


    //!!! UPDATES !!!//
    @Update
    suspend fun updateGrade(updatedGrade: SubjectStudentGrade)


    //!!! DELETIONS !!!//
    @Delete
    suspend fun deleteGrade(gradeToDelete: SubjectStudentGrade)


    //!!! QUERIES !!!//

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