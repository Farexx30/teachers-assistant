package com.example.teachersassistant.models.repositories.subjectstudent

import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto

interface ISubjectStudentRepository {
    abstract suspend fun assignStudentToSubject(newSubjectStudentsDtos: List<SubjectStudentDto>)
    abstract suspend fun removeStudentFromSubject(subjectStudentToRemoveDto: SubjectStudentDto)
    abstract suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>
    abstract suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long, teacherId: Long): List<StudentDto>
}