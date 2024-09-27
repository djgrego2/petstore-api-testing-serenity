package com.petstore.models

data class Pet(
    val id: Long? = null,
    val category: Category? = null,
    val name: String,
    val photoUrls: List<String>,
    val tags: List<Tag>? = null,
    val status: Status
)

data class Category(
    val id: Long? = null,
    val name: String
)

data class Tag(
    val id: Long? = null,
    val name: String
)

enum class Status {
    available, pending, sold
}