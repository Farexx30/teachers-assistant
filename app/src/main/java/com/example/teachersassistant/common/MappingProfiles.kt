package com.example.teachersassistant.common

import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.student.SubjectStudentGradeDto
import com.example.teachersassistant.dtos.subject.SubjectStudentDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.student.SubjectStudent
import com.example.teachersassistant.models.entities.subject.Subject
import com.example.teachersassistant.models.entities.subject.SubjectDate
import com.example.teachersassistant.models.entities.student.SubjectStudentGrade
import com.example.teachersassistant.models.entities.user.User
import org.mindrot.jbcrypt.BCrypt

//!!! USER MAPPERS!!!//
fun User.mapToUserBasicInfoDto(): UserBasicInfoDto {
    return UserBasicInfoDto(
        id = this.id,
        username = this.username
    )
}

fun RegisterOrLoginUserDto.mapToUser(): User {
    val passwordHash = BCrypt.hashpw(this.rawPassword, BCrypt.gensalt())
    return User(
        id = 0,
        username = this.username,
        passwordHash = passwordHash
    )
}


//!!! SUBJECT MAPPERS!!!//
fun SubjectBasicInfoDto.mapToSubject(): Subject {
    return Subject(
        id = this.id,
        name = this.name,
        teacherId = 0
    )
}

fun Subject.mapToSubject(): SubjectBasicInfoDto {
    return SubjectBasicInfoDto(
        id = this.id,
        name = this.name,
    )
}

fun List<Subject>.mapToSubjectDtos(): List<SubjectBasicInfoDto> {
    return this.map {
        it.mapToSubject()
    }
}

fun SubjectDateDto.mapToSubjectDate(): SubjectDate {
    return SubjectDate(
        id = this.id,
        subjectId = 0,
        day = this.day,
        startHour = this.startHour,
        endHour = this.endHour
    )
}



//!!! STUDENT MAPPERS!!!//
fun StudentDto.mapToStudent(): Student {
    return Student(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        albumNumber = this.albumNumber,
        teacherId = 0
    )
}

fun Student.mapToStudentDto(): StudentDto {
    return StudentDto(
        id = this.id,
        firstName = this.firstName,
        lastName = this.lastName,
        albumNumber = this.albumNumber,
    )
}

fun List<Student>.mapToStudentsDtos(): List<StudentDto> {
    return this.map {
        it.mapToStudentDto()
    }
}

fun SubjectStudentGrade.mapToSubjectStudentGradeDto(): SubjectStudentGradeDto {
    return SubjectStudentGradeDto(
        id = this.id,
        gradeTitle = this.gradeTitle,
        grade = this.grade,
        gradeComment = this.gradeComment
    )
}

fun SubjectStudentDto.mapToSubjectStudent(): SubjectStudent {
    return SubjectStudent(
        subjectId = this.subjectId,
        studentId = this.studentId
    )
}



