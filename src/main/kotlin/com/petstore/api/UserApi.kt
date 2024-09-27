package com.petstore.api

import com.petstore.models.User
import com.petstore.utils.ApiUtils
import io.restassured.response.Response
import net.serenitybdd.rest.SerenityRest

class UserApi {
    private val baseRequest = ApiUtils.getBaseRequest()

    fun createUser(user: User): Response = SerenityRest.given(baseRequest)
        .body(user)
        .post("/user")

    fun createUsersWithList(users: List<User>): Response = SerenityRest.given(baseRequest)
        .body(users)
        .post("/user/createWithList")

    fun getUserByUsername(username: String): Response = SerenityRest.given(baseRequest)
        .get("/user/$username")

    fun updateUser(username: String, user: User): Response = SerenityRest.given(baseRequest)
        .body(user)
        .put("/user/$username")

    fun deleteUser(username: String): Response = SerenityRest.given(baseRequest)
        .delete("/user/$username")

    fun loginUser(username: String, password: String): Response = SerenityRest.given(baseRequest)
        .queryParam("username", username)
        .queryParam("password", password)
        .get("/user/login")

    fun logoutUser(): Response = SerenityRest.given(baseRequest)
        .get("/user/logout")
}