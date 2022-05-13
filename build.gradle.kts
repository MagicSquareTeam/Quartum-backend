@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    idea
    java
    id("org.springframework.boot") version libs.versions.spring.boot.get()
    id("io.spring.dependency-management") version libs.versions.spring.dependency.management.get()
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.spring") version libs.versions.kotlin.get()
    id("org.jlleitschuh.gradle.ktlint") version libs.versions.ktlintPlugin.get()
    id("com.github.johnrengelman.shadow") version libs.versions.shadow.get()
    id("org.jetbrains.dokka") version libs.versions.dokka.get()
    application
}

idea {
    project {
        jdkName = libs.versions.java.get()
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(libs.versions.java.get())
    }

    tasks.withType<JavaCompile> {
        sourceCompatibility = libs.versions.java.get()
        targetCompatibility = libs.versions.java.get()
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = libs.versions.java.get()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }
}

gradle.buildFinished {
    copy {
        from("$buildDir/libs/$name-$version.jar")
        into("$buildDir/libs")
        rename {
            "app.jar"
        }
    }
}

tasks.wrapper {
    gradleVersion = libs.versions.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}

buildscript {
    repositories {
        mavenCentral()
    }
}

allprojects {
    group = "magic-square"
    version = "0.0.2-SNAPSHOT"
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

    implementation(libs.bundles.spring.boot.starter)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.postgresql)
    implementation(libs.mongo)
    implementation(libs.bundles.persistence.pack)
    implementation(libs.bundles.logging)
    implementation(libs.jjwt)

    testImplementation(libs.bundles.kotest)
}
