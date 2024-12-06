package com.example.foodforeveryone.model

data class LoginResponse(
    val token: String, // Assuming the API sends a token on successful login
    val message: String // Optional message or error message
)
