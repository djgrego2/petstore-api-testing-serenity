package com.petstore.models

import java.time.OffsetDateTime

data class Order(
    val id: Long? = null,
    val petId: Long,
    val quantity: Int,
    val shipDate: OffsetDateTime? = null,
    val status: OrderStatus,
    val complete: Boolean
)

enum class OrderStatus {
    placed, approved, delivered
}