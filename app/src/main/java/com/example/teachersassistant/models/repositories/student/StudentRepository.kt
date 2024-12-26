package com.example.teachersassistant.models.repositories.student

import com.example.teachersassistant.common.mapToStudent
import com.example.teachersassistant.common.mapToStudentDto
import com.example.teachersassistant.common.mapToStudentsDtos
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.student.StudentWithGradesDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
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

    override suspend fun updateStudent(updatedStudentDto: StudentDto, teacherId: Long) {
        val updatedStudent = updatedStudentDto.mapToStudent()
        updatedStudent.teacherId = teacherId

        studentDao.updateStudent(updatedStudent)
    }

    override suspend fun deleteStudent(studentToDeleteDto: StudentDto) {
        val studentToDelete = studentToDeleteDto.mapToStudent()

        studentDao.deleteStudent(studentToDelete)
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

    override suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto> {
        val studentsDtos = studentDao.getNotSubjectStudentsBySubjectId(subjectId)
        return studentsDtos
    }

    override suspend fun getSubjectStudentsWithGradesById(subjectId:Long, studentId: Long): StudentWithGradesDto {
        //Here we don't need to map to dto because we already returns dto from dao (cuz we are returning data from multiple tables):
        val studentsDtos = studentDao.getStudentDataById(studentId)
        val gradesDtos = studentDao.getSubjectStudentGrades(subjectId, studentId)

        val studentWithGradesDto = StudentWithGradesDto(studentsDtos, gradesDtos)
        return studentWithGradesDto
    }

    override suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto {
        val gradeDto = studentDao.getGradeById(gradeId)
        return gradeDto
    }
}