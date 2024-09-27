package com.petstore.stepdefinitions

import com.petstore.api.StoreApi
import com.petstore.models.Order
import com.petstore.models.OrderStatus
import io.cucumber.java8.En
import io.restassured.response.Response
import net.thucydides.core.annotations.Steps
import org.junit.Assert
import java.time.OffsetDateTime

class StoreSteps : En {

    @Steps
    private lateinit var storeApi: StoreApi

    private lateinit var order: Order
    private lateinit var response: Response

    init {
        Given("an order for pet ID {long} with quantity {int}") { petId: Long, quantity: Int ->
            order = Order(
                petId = petId,
                quantity = quantity,
                shipDate = OffsetDateTime.now(),
                status = OrderStatus.placed,
                complete = false
            )
        }

        When("I place the order") {
            response = storeApi.placeOrder(order)
        }

        Then("the order is successfully placed") {
            Assert.assertEquals(200, response.statusCode())
            val placedOrder = response.`as`(Order::class.java)
            Assert.assertNotNull(placedOrder.id)
            Assert.assertEquals(order.petId, placedOrder.petId)
            Assert.assertEquals(order.quantity, placedOrder.quantity)
        }

        Given("an order with ID {long} exists in the store") { orderId: Long ->
            order = Order(
                id = orderId,
                petId = 1,
                quantity = 1,
                shipDate = OffsetDateTime.now(),
                status = OrderStatus.placed,
                complete = false
            )
            storeApi.placeOrder(order)
        }

        When("I request the order with ID {long}") { orderId: Long ->
            response = storeApi.getOrderById(orderId)
        }

        Then("the order details are correctly returned") {
            Assert.assertEquals(200, response.statusCode())
            val returnedOrder = response.`as`(Order::class.java)
            Assert.assertEquals(order.id, returnedOrder.id)
            Assert.assertEquals(order.petId, returnedOrder.petId)
            Assert.assertEquals(order.quantity, returnedOrder.quantity)
        }

        When("I delete the order with ID {long}") { orderId: Long ->
            response = storeApi.deleteOrder(orderId)
        }

        Then("the order is successfully deleted") {
            Assert.assertEquals(200, response.statusCode())
        }

        When("I request the store inventory") {
            response = storeApi.getInventory()
        }

        Then("I receive the inventory status") {
            Assert.assertEquals(200, response.statusCode())
            val inventory = response.`as`(Map::class.java)
            Assert.assertTrue(inventory.isNotEmpty())
        }
    }
}