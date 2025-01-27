package com.example.teachersassistant.models.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.teachersassistant.models.constants.DatabaseConstants
import com.example.teachersassistant.constants.Day
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.models.entities.subjectstudent.SubjectStudent
import com.example.teachersassistant.models.entities.subject.Subject
import com.example.teachersassistant.models.entities.subject.SubjectDate

@Dao
interface SubjectDao {
    //!!! INSERTS !!!//
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubject(newSubject: Subject): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubjectDate(newSubjectDate: SubjectDate)


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


    //!!! QUERIES !!!//

    @Query("""
       SELECT id, name, teacherId
       FROM ${DatabaseConstants.TableNames.SUBJECTS}
       WHERE teacherId = :currentUserId
       ORDER BY name COLLATE NOCASE
    """)
    suspend fun getAllCurrentUserSubjectsByUserId(currentUserId: Long): List<SubjectBasicInfoDto>

    @Query("""
       SELECT s.id subjectId, s.name subjectName, sd.startHour subjectStartHour, sd.endHour subjectEndHour
       FROM ${DatabaseConstants.TableNames.SUBJECTS} s
       JOIN ${DatabaseConstants.TableNames.SUBJECT_DATE} sd ON sd.subjectId = s.id
       WHERE sd.day = :day AND s.teacherId = :currentUserId
       ORDER BY sd.day, sd.startHour, sd.endHour, s.name COLLATE NOCASE
    """)
    suspend fun getSubjectsWithHours(day: Day, currentUserId: Long): List<SubjectAndHoursDto>

    @Query("""
       SELECT id, name
       FROM ${DatabaseConstants.TableNames.SUBJECTS}
       WHERE id = :subjectId
       LIMIT 1
    """)
    suspend fun getSubjectBasicInfoById (subjectId: Long): SubjectBasicInfoDto

    @Query("""
       SELECT id, day, startHour, endHour
       FROM ${DatabaseConstants.TableNames.SUBJECT_DATE}
       WHERE subjectId = :subjectId
       ORDER BY day, startHour, endHour
    """)
    suspend fun getSubjectDatesBySubjectId(subjectId: Long): List<SubjectDateDto>

    @Query("""
       SELECT id, day, startHour, endHour
       FROM ${DatabaseConstants.TableNames.SUBJECT_DATE}
       WHERE id = :dateId
       LIMIT 1
    """)
    suspend fun getSubjectDateById(dateId: Long): SubjectDateDto
}