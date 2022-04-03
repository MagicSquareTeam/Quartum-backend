@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("org.springframework.boot") version libs.versions.spring.boot.get()
    id("io.spring.dependency-management") version libs.versions.spring.dependency.management.get()
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.spring") version libs.versions.kotlin.get()
    id("org.jlleitschuh.gradle.ktlint") version libs.versions.ktlintPlugin.get()
    application
}

java {
    withSourcesJar()
}

tasks.findByName("build")?.mustRunAfter("clean")

application {
    mainClass.set("magicsquare.quartumbackend.QuartumBackendApplicationKt")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktlint {
    version.set(libs.versions.ktlint.get())
    outputToConsole.set(true)
    outputColorName.set("RED")
    ignoreFailures.set(true)
    additionalEditorconfigFile.set(file("../.editorconfig"))
}

dependencies {
    implementation(project(":service-interface"))

    implementation(libs.bundles.spring.boot.starter)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.postgresql)
    implementation(libs.mongo)
    implementation(libs.bundles.persistence.pack)
    implementation(libs.bundles.logging)
    implementation(libs.jjwt)

    testImplementation(libs.bundles.kotest)
}
