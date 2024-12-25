package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teachersassistant.common.DatabaseTableName
import com.example.teachersassistant.common.Day
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.models.entities.student.SubjectStudent
import com.example.teachersassistant.models.entities.subject.Subject
import com.example.teachersassistant.models.entities.subject.SubjectDate

@Dao
interface SubjectDao {
    //!!! INSERTS !!!//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubject(newSubject: Subject)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubjectDate(newSubjectDate: SubjectDate)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun assignStudentToSubject(newSubjectStudent: SubjectStudent)


    //!!! UPDATES !!!//

    @Update
    suspend fun updateSubject(updatedSubject: Subject)

    @Update
    suspend fun updateSubjectDate(updatedSubjectDate: SubjectDate)


    //!!! DELETIONS !!!//

    @Delete
    suspend fun deleteSubject(subjectToDelete: Subject)

    @Delete
    suspend fun deleteSubjectDate(subjectDateToDelete: SubjectDate)

    @Delete
    suspend fun removeStudentFromSubject(subjectStudentToRemove: SubjectStudent)



    //!!! QUERIES !!!//

    @Query("""
       SELECT id, name, teacherId
       FROM ${DatabaseTableName.SUBJECTS}
       WHERE teacherId = :currentUserId
    """)
    suspend fun getAllCurrentUserSubjectsByUserId(currentUserId: Long): List<Subject>

    @Query("""
       SELECT s.id subjectId, s.name subjectName, sd.startHour subjectStartHour, sd.endHour subjectEndHour
       FROM ${DatabaseTableName.SUBJECTS} s
       JOIN ${DatabaseTableName.SUBJECT_DATE} sd ON sd.subjectId = s.id
       WHERE sd.day = :day
       ORDER BY sd.day, sd.startHour, sd.endHour
    """)
    suspend fun getSubjectsWithHoursByDay(day: Day): SubjectAndHoursDto

    @Query("""
       SELECT id, name
       FROM ${DatabaseTableName.SUBJECTS}
       WHERE id = :subjectId
       LIMIT 1
    """)
    suspend fun getSubjectBasicInfoById (subjectId: Long): SubjectBasicInfoDto

    @Query("""
       SELECT id, day, startHour, endHour
       FROM ${DatabaseTableName.SUBJECT_DATE}
       WHERE subjectId = :subjectId
       ORDER BY day, startHour, endHour
    """)
    suspend fun getSubjectDatesBySubjectId(subjectId: Long): List<SubjectDateDto>
}