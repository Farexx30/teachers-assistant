package com.example.teachersassistant.models.repositories.student

import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto

interface IStudentRepository {
    abstract suspend fun insertStudent(newStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun updateStudent(updatedStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun deleteStudent(studentToDeleteDto: StudentDto)
    abstract suspend fun getAllCurrentUserStudents(teacherId: Long): List<StudentDto>
    abstract suspend fun getStudentDataById(studentId: Long): StudentDto

}