package com.example.teachersassistant.models.repositories.subject

import com.example.teachersassistant.constants.Day
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto

interface ISubjectRepository {
    abstract suspend fun insertSubject(newSubjectDto: SubjectBasicInfoDto, teacherId: Long): Long
    abstract suspend fun insertSubjectDate(newSubjectDateDto: SubjectDateDto, subjectId: Long)
    abstract suspend fun updateSubject(updatedSubjectDto: SubjectBasicInfoDto, teacherId: Long)
    abstract suspend fun updateSubjectDate(updatedSubjectDateDto: SubjectDateDto, subjectId: Long)
    abstract suspend fun deleteSubject(subjectToDeleteDto: SubjectBasicInfoDto)
    abstract suspend fun deleteSubjectDate(subjectDateToDeleteDto: SubjectDateDto)
    abstract suspend fun getSubjectBasicInfoById (subjectId: Long): SubjectBasicInfoDto
    abstract suspend fun getAllCurrentUserSubjects(currentUserId: Long): List<SubjectBasicInfoDto>
    abstract suspend fun getSubjectsWithHours(day: Day, currentUserId: Long): List<SubjectAndHoursDto>
    abstract suspend fun getSubjectWithDates(subjectId: Long): Pair<SubjectBasicInfoDto, List<SubjectDateDto>>
    abstract suspend fun getSubjectDateById(dateId: Long): SubjectDateDto
}