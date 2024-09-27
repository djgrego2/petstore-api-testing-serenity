import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.20"
    id("net.serenity-bdd.serenity-gradle-plugin") version "3.6.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.20"
}

group = "com.petstore"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val cucumberVersion = "7.11.1"
val serenityVersion = "3.6.22"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("net.serenity-bdd:serenity-core:$serenityVersion")
    implementation("net.serenity-bdd:serenity-cucumber:$serenityVersion")
    implementation("net.serenity-bdd:serenity-rest-assured:$serenityVersion")
    implementation("io.cucumber:cucumber-java8:$cucumberVersion")
    implementation("io.cucumber:cucumber-junit:$cucumberVersion")
    implementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("io.rest-assured:rest-assured:5.3.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("cucumber.options", "--plugin pretty")
}

tasks.test {
    testLogging {
        events("passed", "skipped", "failed")
    }
}

allOpen {
    annotation("net.thucydides.core.annotations.Steps")
}