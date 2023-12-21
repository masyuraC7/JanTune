package com.jantune.heartdisease.data.remote.response


data class ApiResponse<T>(
    val data: T?,
    val message: String?
)

data class UserRegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class UserLoginRequest(
    val email: String,
    val password: String
)

data class UserLoginResponse(
    val token: String?
)
