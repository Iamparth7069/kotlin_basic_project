package com.example.kotlin_basic.data.model

data class LoginResponse(
    val message: String,
    val status: Int,
    val data: UserData
)

data class UserData(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val accessToken: String,
    val roleId: Int
)