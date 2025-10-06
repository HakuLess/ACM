import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.24"
    application
}

kotlin {
    jvmToolchain(16)
}

group = "me.haku"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}



tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "16"
}