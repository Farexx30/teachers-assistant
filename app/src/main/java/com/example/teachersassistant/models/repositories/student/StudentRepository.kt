package com.example.teachersassistant.models.repositories.student

import com.example.teachersassistant.dtos.mapToStudent
import com.example.teachersassistant.dtos.mapToSubjectStudentGrade
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.models.daos.StudentDao
import javax.inject.Inject

class StudentRepository @Inject constructor(
    private val studentDao: StudentDao,
): IStudentRepository {
    override suspend fun insertStudent(newStudentDto: StudentDto, teacherId: Long) {
        val newStudent = newStudentDto.mapToStudent()
        newStudent.teacherId = teacherId

        studentDao.insertStudent(newStudent)
    }

    override suspend fun insertGrade(
        newGradeDto: SubjectStudentGradeDto,
        subjectId: Long,
        studentId: Long
    ) {
        val newGrade = newGradeDto.mapToSubjectStudentGrade()
        newGrade.subjectId = subjectId
        newGrade.studentId = studentId

        studentDao.insertGrade(newGrade)
    }

    override suspend fun updateStudent(updatedStudentDto: StudentDto, teacherId: Long) {
        val updatedStudent = updatedStudentDto.mapToStudent()
        updatedStudent.teacherId = teacherId

        studentDao.updateStudent(updatedStudent)
    }

    override suspend fun updateGrade(
        updatedGradeDto: SubjectStudentGradeDto,
        subjectId: Long,
        studentId: Long
    ) {
        val updatedGrade = updatedGradeDto.mapToSubjectStudentGrade()
        updatedGrade.subjectId = subjectId
        updatedGrade.studentId = studentId

        studentDao.updateGrade(updatedGrade)
    }

    override suspend fun deleteStudent(studentToDeleteDto: StudentDto) {
        val studentToDelete = studentToDeleteDto.mapToStudent()

        studentDao.deleteStudent(studentToDelete)
    }

    override suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto) {
        val gradeToDelete = gradeToDeleteDto.mapToSubjectStudentGrade()

        studentDao.deleteGrade(gradeToDelete)
    }

    override suspend fun getAllCurrentUserStudents(teacherId: Long): List<StudentDto> {
        val studentsDtos = studentDao.getAllCurrentUserStudentsById(teacherId)
        return studentsDtos
    }

    override suspend fun getStudentDataById(studentId: Long): StudentDto {
        val studentDto = studentDao.getStudentDataById(studentId)
        return studentDto
    }

    override suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto> {
        val studentsDtos = studentDao.getSubjectStudentsBySubjectId(subjectId)
        return studentsDtos
    }

    override suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long, teacherId: Long): List<StudentDto> {
        val studentsDtos = studentDao.getNotSubjectStudentsBySubjectId(subjectId, teacherId)
        return studentsDtos
    }

    override suspend fun getSubjectStudentWithGrades(subjectId:Long, studentId: Long): Pair<StudentDto, List<SubjectStudentGradeDto>> {
        //Here we don't need to map to dto because we already returns dto from dao (cuz we are returning data from multiple tables):
        val studentDto = studentDao.getStudentDataById(studentId)
        val gradesDtos = studentDao.getSubjectStudentGrades(subjectId, studentId)

        return Pair(studentDto, gradesDtos)
    }

    override suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto {
        val gradeDto = studentDao.getGradeById(gradeId)
        return gradeDto
    }
}