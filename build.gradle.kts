import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    idea
    java
    kotlin("jvm") version libs.versions.kotlin.get() apply false
    kotlin("plugin.spring") version libs.versions.kotlin.get() apply false
}

idea {
    project {
        jdkName = libs.versions.java.get()
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(libs.versions.java.get())
    }
}

tasks.register<Copy>("copyToLib") {
    from(configurations["compileClasspath"])
    into("$buildDir/libs")
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "service.src.main.kotlin.magicsquare.quartumbackend.QuartumBackendApplication"
    }
    from(configurations.compileClasspath.get().map { if (it.isDirectory()) it else zipTree(it) })
}

tasks.register("stage") {
    dependsOn("clean")
    dependsOn("build")
    dependsOn("copyToLib")
    tasks.findByName("build")?.mustRunAfter("clean")
}

allprojects {
    group = "magic-square"
    version = "0.0.2-SNAPSHOT"
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
