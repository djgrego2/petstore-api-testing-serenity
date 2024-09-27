package com.petstore.stepdefinitions

import com.petstore.api.PetApi
import com.petstore.models.Pet
import com.petstore.models.Category
import com.petstore.models.Tag
import com.petstore.models.Status
import io.cucumber.java8.En
import io.restassured.response.Response
import net.thucydides.core.annotations.Steps
import org.junit.Assert

class PetSteps : En {

    @Steps
    private lateinit var petApi: PetApi

    private lateinit var pet: Pet
    private lateinit var response: Response

    init {
        Given("a pet with name {string} and status {string}") { name: String, status: String ->
            pet = Pet(
                name = name,
                status = Status.valueOf(status),
                photoUrls = listOf("http://example.com/photo.jpg"),
                category = Category(1, "TestCategory"),
                tags = listOf(Tag(1, "TestTag"))
            )
        }

        When("I add the pet to the store") {
            response = petApi.addNewPet(pet)
        }

        Then("the pet is successfully added") {
            Assert.assertEquals(200, response.statusCode())
            val addedPet = response.`as`(Pet::class.java)
            Assert.assertEquals(pet.name, addedPet.name)
            Assert.assertEquals(pet.status, addedPet.status)
        }

        Given("a pet with ID {long} exists in the store") { petId: Long ->
            pet = Pet(
                id = petId,
                name = "ExistingPet",
                status = Status.available,
                photoUrls = listOf("http://example.com/photo.jpg")
            )
            petApi.addNewPet(pet)
        }

        When("I request the pet with ID {long}") { petId: Long ->
            response = petApi.getPetById(petId)
        }

        Then("the pet details are correctly returned") {
            Assert.assertEquals(200, response.statusCode())
            val returnedPet = response.`as`(Pet::class.java)
            Assert.assertEquals(pet.id, returnedPet.id)
            Assert.assertEquals(pet.name, returnedPet.name)
        }

        When("I update the pet's name to {string} and status to {string}") { newName: String, newStatus: String ->
            pet = pet.copy(name = newName, status = Status.valueOf(newStatus))
            response = petApi.updatePet(pet)
        }

        Then("the pet is successfully updated") {
            Assert.assertEquals(200, response.statusCode())
            val updatedPet = response.`as`(Pet::class.java)
            Assert.assertEquals(pet.name, updatedPet.name)
            Assert.assertEquals(pet.status, updatedPet.status)
        }

        When("I delete the pet with ID {long}") { petId: Long ->
            response = petApi.deletePet(petId, "special-key")
        }

        Then("the pet is successfully deleted") {
            Assert.assertEquals(200, response.statusCode())
        }

        When("I find pets by status {string}") { status: String ->
            response = petApi.findPetsByStatus(status)
        }

        Then("I receive a list of pets with status {string}") { status: String ->
            Assert.assertEquals(200, response.statusCode())
            val pets = response.`as`(Array<Pet>::class.java)
            Assert.assertTrue(pets.isNotEmpty())
            Assert.assertTrue(pets.all { it.status == Status.valueOf(status) })
        }
    }
}