package com.petstore.models

data class User(
    val id: Long? = null,
    val username: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val phone: String,
    val userStatus: Int
)