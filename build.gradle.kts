import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    "java-library"
    idea
    kotlin("jvm") version libs.versions.kotlin.get() apply false
    kotlin("plugin.spring") version libs.versions.kotlin.get() apply false
}

idea {
    project {
        jdkName = libs.versions.java.get()
        languageLevel = org.gradle.plugins.ide.idea.model.IdeaLanguageLevel(libs.versions.java.get())
    }
}


tasks.register<Copy>("copyToLib"){
    from(configurations.compileClasspath.get())
    into("$buildDir/libs")
} 

tasks.register("stage") {
    dependsOn("copyToLib")
    dependsOn("clean")
    dependsOn("build")
    tasks.findByName("build")?.mustRunAfter("clean")
}


// gradle.taskGraph.whenReady {
//   taskGraph ->
//     if (taskGraph.hasTask(stage)) {
//       test.enabled = false
//     }
// }

allprojects{
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