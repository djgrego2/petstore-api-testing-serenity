package com.petstore.runners

import io.cucumber.junit.CucumberOptions
import net.serenitybdd.cucumber.CucumberWithSerenity
import org.junit.runner.RunWith

@RunWith(CucumberWithSerenity::class)
@CucumberOptions(
    features = ["src/test/resources/features"],
    glue = ["com.petstore.stepdefinitions"]
)
class TestRunner