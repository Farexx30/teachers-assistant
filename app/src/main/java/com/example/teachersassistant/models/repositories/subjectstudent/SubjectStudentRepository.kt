package com.example.teachersassistant.models.repositories.subjectstudent

import com.example.teachersassistant.dtos.mapToSubjectStudent
import com.example.teachersassistant.dtos.mapToSubjectStudents
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto
import com.example.teachersassistant.models.daos.SubjectStudentDao
import javax.inject.Inject

class SubjectStudentRepository @Inject constructor(
    private val subjectStudentDao: SubjectStudentDao,
) : ISubjectStudentRepository {
    override suspend fun assignStudentToSubject(newSubjectStudentsDtos: List<SubjectStudentDto>) {
        val newSubjectStudents = newSubjectStudentsDtos.mapToSubjectStudents()

        subjectStudentDao.assignStudentsToSubject(newSubjectStudents)
    }

    override suspend fun removeStudentFromSubject(subjectStudentToRemoveDto: SubjectStudentDto) {
        val subjectStudentToRemove = subjectStudentToRemoveDto.mapToSubjectStudent()

        subjectStudentDao.removeStudentFromSubject(subjectStudentToRemove)
    }

    override suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto> {
        val studentsDtos = subjectStudentDao.getSubjectStudentsBySubjectId(subjectId)
        return studentsDtos
    }

    override suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long, teacherId: Long): List<StudentDto> {
        val studentsDtos = subjectStudentDao.getNotSubjectStudentsBySubjectId(subjectId, teacherId)
        return studentsDtos
    }
}