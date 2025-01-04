package com.example.teachersassistant.models.repositories.student

import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.student.StudentWithGradesDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.models.entities.student.SubjectStudentGrade

interface IStudentRepository {
    abstract suspend fun insertStudent(newStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun insertGrade(newGradeDto: SubjectStudentGradeDto, subjectId: Long, studentId: Long)
    abstract suspend fun updateStudent(updatedStudentDto: StudentDto, teacherId: Long)
    abstract suspend fun updateGrade(updatedGradeDto: SubjectStudentGradeDto, subjectId: Long, studentId: Long)
    abstract suspend fun deleteStudent(studentToDeleteDto: StudentDto)
    abstract suspend fun deleteGrade(gradeToDeleteDto: SubjectStudentGradeDto)
    abstract suspend fun getAllCurrentUserStudents(teacherId: Long): List<StudentDto>
    abstract suspend fun getStudentDataById(studentId: Long): StudentDto
    abstract suspend fun getSubjectStudentsBySubjectId(subjectId: Long): List<StudentDto>
    abstract suspend fun getNotSubjectStudentsBySubjectId(subjectId: Long, teacherId: Long): List<StudentDto>
    abstract suspend fun getSubjectStudentWithGrades(subjectId: Long, studentId: Long): Pair<StudentDto, List<SubjectStudentGradeDto>>
    abstract suspend fun getGradeById(gradeId: Long): SubjectStudentGradeDto
}