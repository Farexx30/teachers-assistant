package com.example.teachersassistant.dtos.user

data class RegisterOrLoginUserDto(
    val username: String,
    val rawPassword: String,
)
