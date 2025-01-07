package com.example.teachersassistant.models.repositories.subjectstudentgrade

import com.example.teachersassistant.dtos.mapToSubjectStudentGrade
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.models.daos.SubjectStudentGradeDao
import javax.inject.Inject

class SubjectStudentGradeRepository @Inject constructor(
    private val subjectStudentGradeDao: SubjectStudentGradeDao,
) : ISubjectStudentGradeRepository {
    override suspend fun insertGrade(
        newGradeDto: SubjectStudentGradeDto,
        subjectId: Long,
        studentId: Long
    ) {
        val newGrade = newGradeDto.mapToSubjectStudentGrade()
        newGrade.subjectId = subjectId
        newGrade.studentId = studentId

        subjectStudentGradeDao.insertGrade(newGrade)
    }

    override suspend fun updateGrade(
        updatedGradeDto: SubjectStudentGradeDto,
        subjectId: Long,
        studentId: Long
    ) {
        val updatedGrade = updatedGradeDto.mapToSubjectStudentGrade()
        updatedGrade.subjectId = subjectId
        updatedGrade.studentId = studentId

        subjectStudentGradeDao.updateGrade(updatedGrade)
    }

    override suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto) {
        val gradeToDelete = gradeToDeleteDto.mapToSubjectStudentGrade()

        subjectStudentGradeDao.deleteGrade(gradeToDelete)
    }

    override suspend fun getSubjectStudentGrades(subjectId:Long, studentId: Long): List<SubjectStudentGradeDto> {
        val gradesDtos = subjectStudentGradeDao.getSubjectStudentGrades(subjectId, studentId)
        return gradesDtos
    }

    override suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto {
        val gradeDto = subjectStudentGradeDao.getGradeById(gradeId)
        return gradeDto
    }
}