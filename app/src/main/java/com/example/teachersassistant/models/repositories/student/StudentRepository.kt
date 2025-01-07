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
}