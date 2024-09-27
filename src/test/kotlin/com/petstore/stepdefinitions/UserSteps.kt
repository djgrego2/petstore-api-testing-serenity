package com.petstore.stepdefinitions

import com.petstore.api.UserApi
import com.petstore.models.User
import io.cucumber.java8.En
import io.restassured.response.Response
import net.thucydides.core.annotations.Steps
import org.junit.Assert

class UserSteps : En {

    @Steps
    private lateinit var userApi: UserApi

    private lateinit var user: User
    private lateinit var response: Response

    init {
        Given("a user with username {string} and email {string}") { username: String, email: String ->
            user = User(
                username = username,
                firstName = "Gregorio",
                lastName = "Perez",
                email = email,
                password = "securepass123",
                phone = "1234567890",
                userStatus = 1
            )
        }

        When("I create the user") {
            response = userApi.createUser(user)
        }

        Then("the user is successfully created") {
            Assert.assertEquals(200, response.statusCode())
        }

        Given("a user with username {string} exists") { username: String ->
            user = User(
                username = username,
                firstName = "Gregorio",
                lastName = "Perez",
                email = "gregorio.perez@example.com",
                password = "securepass123",
                phone = "9876543210",
                userStatus = 1
            )
            userApi.createUser(user)
        }

        When("I request the user with username {string}") { username: String ->
            response = userApi.getUserByUsername(username)
        }

        Then("the user details are correctly returned") {
            Assert.assertEquals(200, response.statusCode())
            val returnedUser = response.`as`(User::class.java)
            Assert.assertEquals(user.username, returnedUser.username)
            Assert.assertEquals(user.email, returnedUser.email)
            Assert.assertEquals("Gregorio", returnedUser.firstName)
            Assert.assertEquals("Perez", returnedUser.lastName)
        }

        When("I update the user's email to {string}") { newEmail: String ->
            user = user.copy(email = newEmail)
            response = userApi.updateUser(user.username, user)
        }

        Then("the user is successfully updated") {
            Assert.assertEquals(200, response.statusCode())
        }

        When("I delete the user with username {string}") { username: String ->
            response = userApi.deleteUser(username)
        }

        Then("the user is successfully deleted") {
            Assert.assertEquals(200, response.statusCode())
        }

        When("I login with username {string} and password {string}") { username: String, password: String ->
            response = userApi.loginUser(username, password)
        }

        Then("the login is successful") {
            Assert.assertEquals(200, response.statusCode())
            Assert.assertTrue(response.body().asString().contains("logged in user session:"))
        }

        When("I logout the user") {
            response = userApi.logoutUser()
        }

        Then("the logout is successful") {
            Assert.assertEquals(200, response.statusCode())
        }

        Given("a list of users") {
            user = User(
                username = "gregorio_perez1",
                firstName = "Gregorio",
                lastName = "Perez",
                email = "gregorio.perez1@example.com",
                password = "securepass123",
                phone = "1122334455",
                userStatus = 1
            )
        }

        When("I create the users with list") {
            response = userApi.createUsersWithList(listOf(user))
        }

        Then("the users are successfully created") {
            Assert.assertEquals(200, response.statusCode())
        }
    }
}