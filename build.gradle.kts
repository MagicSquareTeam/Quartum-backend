import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    idea
    java
    kotlin("jvm") version libs.versions.kotlin.get() apply false
    kotlin("plugin.spring") version libs.versions.kotlin.get() apply false
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

idea {
    project {
        jdkName = libs.versions.java.get()
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(libs.versions.java.get())
    }
}

tasks.findByName("build")?.mustRunAfter("clean")

tasks.register<Copy>("stage") {
    dependsOn("clean")
    dependsOn("build")
    val buildDir1 = project(":service").buildDir
    from("$buildDir1/libs")
    into("$buildDir/libs")
    rename {
        "app.jar"
    }
}

allprojects {
    group = "magic-square"
    version = "0.0.2-SNAPSHOT"
}

buildscript {
    repositories {
        mavenCentral()
    }
}

subprojects {

    tasks.withType<JavaCompile> {
        sourceCompatibility = libs.versions.java.get()
        targetCompatibility = libs.versions.java.get()
    }

    tasks.withType<KotlinCompile> {
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

tasks.wrapper {
    gradleVersion = libs.versions.gradle.get()
    distributionType = Wrapper.DistributionType.ALL
}
