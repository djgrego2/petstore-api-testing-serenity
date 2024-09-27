package com.petstore.api

import com.petstore.models.Pet
import com.petstore.utils.ApiUtils
import io.restassured.response.Response
import net.serenitybdd.rest.SerenityRest

class PetApi {
    private val baseRequest = ApiUtils.getBaseRequest()

    fun addNewPet(pet: Pet): Response = SerenityRest.given(baseRequest)
        .body(pet)
        .post("/pet")

    fun updatePet(pet: Pet): Response = SerenityRest.given(baseRequest)
        .body(pet)
        .put("/pet")

    fun findPetsByStatus(status: String): Response = SerenityRest.given(baseRequest)
        .queryParam("status", status)
        .get("/pet/findByStatus")

    fun getPetById(petId: Long): Response = SerenityRest.given(baseRequest)
        .get("/pet/$petId")

    fun updatePetWithFormData(petId: Long, name: String?, status: String?): Response = SerenityRest.given(baseRequest)
        .contentType("application/x-www-form-urlencoded")
        .formParam("name", name)
        .formParam("status", status)
        .post("/pet/$petId")

    fun deletePet(petId: Long, apiKey: String): Response = SerenityRest.given(ApiUtils.getAuthenticatedRequest(apiKey))
        .delete("/pet/$petId")

    fun uploadImage(petId: Long, additionalMetadata: String?, file: ByteArray): Response = SerenityRest.given(baseRequest)
        .multiPart("additionalMetadata", additionalMetadata)
        .multiPart("file", "image.jpg", file)
        .post("/pet/$petId/uploadImage")
}