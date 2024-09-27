package com.petstore.utils

import io.restassured.RestAssured
import io.restassured.specification.RequestSpecification
import com.petstore.config.TestConfig
import io.restassured.http.ContentType

object ApiUtils {
    fun getBaseRequest(): RequestSpecification = RestAssured.given()
        .baseUri(TestConfig.BASE_URL)
        .contentType(ContentType.JSON)
        .accept(ContentType.JSON)

    fun getAuthenticatedRequest(apiKey: String): RequestSpecification = getBaseRequest()
        .header("api_key", apiKey)
}