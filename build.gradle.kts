plugins {
    id("maven-publish")
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.dokka") version "0.10.1"
    kotlin("plugin.serialization") version "1.9.22"
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
    maven("https://jitpack.io")
    maven("https://kotlin.bintray.com/ktor")
    maven("https://kotlin.bintray.com/kotlinx")
}

group = "com.jeluchu.aruxpi"
version = "1.0.0-beta02"

dependencies {
    implementation(libs.bundles.gson)
    implementation(libs.bundles.ktor)
    implementation(libs.bundles.jeluchu)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.bundles.coroutines)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }

    test {
        useJUnitPlatform()
    }

    dokka {
        outputFormat = "html"
        outputDirectory = "$rootDir/docs"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.jeluchu"
            artifactId = "aruxpi"
            version = "1.0.0-beta02"

            from(components["kotlin"])
        }
    }
}