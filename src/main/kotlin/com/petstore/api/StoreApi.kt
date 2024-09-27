package com.petstore.api

import com.petstore.models.Order
import com.petstore.utils.ApiUtils
import io.restassured.response.Response
import net.serenitybdd.rest.SerenityRest

class StoreApi {
    private val baseRequest = ApiUtils.getBaseRequest()

    fun getInventory(): Response = SerenityRest.given(baseRequest)
        .get("/store/inventory")

    fun placeOrder(order: Order): Response = SerenityRest.given(baseRequest)
        .body(order)
        .post("/store/order")

    fun getOrderById(orderId: Long): Response = SerenityRest.given(baseRequest)
        .get("/store/order/$orderId")

    fun deleteOrder(orderId: Long): Response = SerenityRest.given(baseRequest)
        .delete("/store/order/$orderId")
}