package com.example.teachersassistant.models.repositories.subject

import com.example.teachersassistant.constants.Day
import com.example.teachersassistant.dtos.mapToSubject
import com.example.teachersassistant.dtos.mapToSubjectDate
import com.example.teachersassistant.dtos.mapToSubjectStudent
import com.example.teachersassistant.dtos.mapToSubjectStudents
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto
import com.example.teachersassistant.models.daos.SubjectDao
import javax.inject.Inject

class SubjectRepository @Inject constructor(
    private val subjectDao: SubjectDao,
): ISubjectRepository {
    override suspend fun insertSubject(newSubjectDto: SubjectBasicInfoDto, teacherId: Long): Long {
        val newSubject = newSubjectDto.mapToSubject()
        newSubject.teacherId = teacherId

        val insertedSubjectId = subjectDao.insertSubject(newSubject)
        return insertedSubjectId
    }

    override suspend fun insertSubjectDate(newSubjectDateDto: SubjectDateDto, subjectId: Long) {
        val newSubjectDate = newSubjectDateDto.mapToSubjectDate()
        newSubjectDate.subjectId = subjectId

        subjectDao.insertSubjectDate(newSubjectDate)
    }


    //updateSubject fun actually updates subject name only:
    override suspend fun updateSubject(updatedSubjectDto: SubjectBasicInfoDto, teacherId: Long) {
        val updatedSubject = updatedSubjectDto.mapToSubject()
        updatedSubject.teacherId = teacherId

        subjectDao.updateSubject(updatedSubject)
    }

    override suspend fun updateSubjectDate(updatedSubjectDateDto: SubjectDateDto, subjectId: Long) {
        val updatedSubjectDate = updatedSubjectDateDto.mapToSubjectDate()
        updatedSubjectDate.subjectId = subjectId

        subjectDao.updateSubjectDate(updatedSubjectDate)
    }


    override suspend fun deleteSubject(subjectToDeleteDto: SubjectBasicInfoDto) {
        val subjectToDelete = subjectToDeleteDto.mapToSubject()

        subjectDao.deleteSubject(subjectToDelete)
    }

    override suspend fun deleteSubjectDate(subjectDateToDeleteDto: SubjectDateDto) {
        val subjectDateToDelete = subjectDateToDeleteDto.mapToSubjectDate()

        subjectDao.deleteSubjectDate(subjectDateToDelete)
    }

    override suspend fun getSubjectBasicInfoById(subjectId: Long): SubjectBasicInfoDto {
        val subjectDto = subjectDao.getSubjectBasicInfoById(subjectId)
        return subjectDto
    }

    override suspend fun getAllCurrentUserSubjects(currentUserId: Long): List<SubjectBasicInfoDto> {
        val subjectsDtos = subjectDao.getAllCurrentUserSubjectsByUserId(currentUserId)
        return subjectsDtos
    }

    override suspend fun getSubjectsWithHours(day: Day, currentUserId: Long): List<SubjectAndHoursDto> {
        val subjectsWithHoursDtos = subjectDao.getSubjectsWithHours(day, currentUserId)
        return subjectsWithHoursDtos
    }

    override suspend fun getSubjectWithDates(subjectId: Long): Pair<SubjectBasicInfoDto, List<SubjectDateDto>> {
        val subjectDto = subjectDao.getSubjectBasicInfoById(subjectId)
        val subjectDatesDtos = subjectDao.getSubjectDatesBySubjectId(subjectId)

        return Pair(subjectDto, subjectDatesDtos)
    }

    override suspend fun getSubjectDateById(dateId: Long): SubjectDateDto {
        val subjectDateDto = subjectDao.getSubjectDateById(dateId)
        return subjectDateDto
    }
}