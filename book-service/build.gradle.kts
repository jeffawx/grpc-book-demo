import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.0"
    `maven-publish`
}

allprojects {
    repositories {
        mavenLocal()
        maven("https://artistry.airwallex.com/repository/lib-release/libs-release-local")
        mavenCentral()
    }

    apply(plugin = "maven-publish")

    publishing {
        repositories {
            maven {
                val repoKey = if (version.toString().endsWith("-SNAPSHOT")) "snapshot" else "release"
                url = uri("https://artistry.airwallex.com/repository/lib-$repoKey/libs-$repoKey-local/")

                credentials {
                    username = System.getenv("ARTISTRY_USERNAME")
                    password = System.getenv("ARTISTRY_PASSWORD")
                }
            }
        }
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "java-library")

    java.sourceCompatibility = JavaVersion.VERSION_11

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    publishing {
        publications {
            val sourcesJar by tasks.registering(Jar::class) {
                archiveClassifier.set("sources")
                from(sourceSets.main.get().allSource)
            }

            create<MavenPublication>("default") {
                from(components["java"])
                artifact(sourcesJar.get())
            }
        }
    }
}
