package com.example.teachersassistant.models.repositories.subject

import com.example.teachersassistant.common.Day
import com.example.teachersassistant.common.mapToSubject
import com.example.teachersassistant.common.mapToSubjectDate
import com.example.teachersassistant.common.mapToSubjectDtos
import com.example.teachersassistant.common.mapToSubjectStudent
import com.example.teachersassistant.dtos.subject.SubjectAndHoursDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.subject.SubjectStudentDto
import com.example.teachersassistant.dtos.subject.SubjectWithDatesDto
import com.example.teachersassistant.models.daos.SubjectDao
import com.example.teachersassistant.models.entities.student.SubjectStudent
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

    override suspend fun assignStudentToSubject(newSubjectStudentDto: SubjectStudentDto) {
        val newSubjectStudent = newSubjectStudentDto.mapToSubjectStudent()

        subjectDao.assignStudentToSubject(newSubjectStudent)
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

    override suspend fun deleteSubject(subjectDateToDeleteDto: SubjectDateDto) {
        val subjectDateToDelete = subjectDateToDeleteDto.mapToSubjectDate()

        subjectDao.deleteSubjectDate(subjectDateToDelete)
    }

    override suspend fun removeStudentFromSubject(subjectStudentToRemoveDto: SubjectStudentDto) {
        val subjectStudentToRemove = subjectStudentToRemoveDto.mapToSubjectStudent()

        subjectDao.removeStudentFromSubject(subjectStudentToRemove)
    }


    override suspend fun getSubjectBasicInfoById(subjectId: Long): SubjectBasicInfoDto {
        val subjectDto = subjectDao.getSubjectBasicInfoById(subjectId)
        return subjectDto
    }

    override suspend fun getAllCurrentUserSubjects(currentUserId: Long): List<SubjectBasicInfoDto> {
        //Here we don't need to map to dto because we already returns dto from dao:
        val subjectsDtos = subjectDao.getAllCurrentUserSubjectsByUserId(currentUserId)
        return subjectsDtos
    }

    override suspend fun getSubjectsWithHoursByDay(day: Day): SubjectAndHoursDto {
        val subjectsWithHoursDto = subjectDao.getSubjectsWithHoursByDay(day)

        return subjectsWithHoursDto
    }

    override suspend fun getSubjectWithDates(subjectId: Long): SubjectWithDatesDto {
        //Here we don't need to map to dto because we already returns dto from dao (cuz we are returning data from multiple tables):
        val subjectDto = subjectDao.getSubjectBasicInfoById(subjectId)
        val subjectDates = subjectDao.getSubjectDatesBySubjectId(subjectId)

        val subjectWithDatesDto = SubjectWithDatesDto(subjectDto, subjectDates)
        return subjectWithDatesDto
    }

}