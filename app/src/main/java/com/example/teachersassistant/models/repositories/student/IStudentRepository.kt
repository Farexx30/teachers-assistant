package com.example.teachersassistant.models.repositories.student

import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.student.StudentWithGradesDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.models.entities.student.SubjectStudentGrade

interface IStudentRepository {
    abstract suspend fun insertStudent(newStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun updateStudent(updatedStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun deleteStudent(studentToDeleteDto: StudentDto)
    abstract suspend fun getAllCurrentUserStudents(teacherId: Long): List<StudentDto>
    abstract suspend fun getStudentDataById(studentId: Long): StudentDto
    abstract suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>
    abstract suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>
    abstract suspend fun getSubjectStudentsWithGradesById(subjectId: Long, studentId: Long): StudentWithGradesDto
    abstract suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto
}