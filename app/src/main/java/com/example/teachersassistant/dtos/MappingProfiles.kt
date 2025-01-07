package com.example.teachersassistant.dtos

import com.example.teachersassistant.dtos.user.RegisterOrLoginUserDto
import com.example.teachersassistant.dtos.student.StudentDto
import com.example.teachersassistant.dtos.subject.SubjectBasicInfoDto
import com.example.teachersassistant.dtos.subject.SubjectDateDto
import com.example.teachersassistant.dtos.subjectstudentgrade.SubjectStudentGradeDto
import com.example.teachersassistant.dtos.subjectstudent.SubjectStudentDto
import com.example.teachersassistant.dtos.user.UserBasicInfoDto
import com.example.teachersassistant.models.entities.student.Student
import com.example.teachersassistant.models.entities.subject.Subject
import com.example.teachersassistant.models.entities.subjectstudent.SubjectStudent
import com.example.teachersassistant.models.entities.subject.SubjectDate
import com.example.teachersassistant.models.entities.subjectstudentgrade.SubjectStudentGrade
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


//!!! SUBJECT AND SUBJECT DATE MAPPERS!!!//
fun SubjectBasicInfoDto.mapToSubject(): Subject {
    return Subject(
        id = this.id,
        name = this.name,
        teacherId = 0
    )
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


//!!!SUBJECT STUDENT MAPPERS!!!//
fun SubjectStudentDto.mapToSubjectStudent(): SubjectStudent {
    return SubjectStudent(
        subjectId = this.subjectId,
        studentId = this.studentId
    )
}

fun List<SubjectStudentDto>.mapToSubjectStudents(): List<SubjectStudent> {
    return this.map {
        it.mapToSubjectStudent()
    }
}


//!!!SUBJECT STUDENT GRADE MAPPERS!!!//
fun SubjectStudentGradeDto.mapToSubjectStudentGrade(): SubjectStudentGrade {
    return SubjectStudentGrade(
        id = this.id,
        subjectId = 0L,
        studentId = 0L,
        gradeTitle = this.title,
        grade = this.grade,
        gradeComment =  this.comment
    )
}

