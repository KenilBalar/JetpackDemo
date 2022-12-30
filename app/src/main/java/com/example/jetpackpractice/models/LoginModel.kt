package com.example.jetpackpractice.models

data class LoginModel(
    val email: String? = null ?: "",
    val password: String? = null ?: ""
)


