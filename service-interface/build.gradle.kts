@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    `java-library`
    kotlin("jvm") version libs.versions.kotlin.get()
    id("org.jlleitschuh.gradle.ktlint") version libs.versions.ktlintPlugin.get()
}

java {
    withSourcesJar()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
}
