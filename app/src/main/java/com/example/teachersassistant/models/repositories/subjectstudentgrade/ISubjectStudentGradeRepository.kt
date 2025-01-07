package com.example.teachersassistant.models.repositories.subjectstudentgrade

import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto

interface ISubjectStudentGradeRepository {
    abstract suspend fun insertGrade(newGradeDto: SubjectStudentGradeDto, subjectId: Long, studentId: Long)
    abstract suspend fun updateGrade(updatedGradeDto: SubjectStudentGradeDto, subjectId: Long, studentId: Long)
    abstract suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto)
    abstract suspend fun getSubjectStudentGrades(subjectId: Long, studentId: Long): List<SubjectStudentGradeDto>
    abstract suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto
}