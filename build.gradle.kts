import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21"
    id("me.champeau.gradle.jmh") version "0.5.2"
}

group = "ru.nsu.mmp.ru.nsu.mmp.pds"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven { url = uri("https://plugins.gradle.org/m2/") }
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("me.champeau.gradle:jmh-gradle-plugin:0.5.2")
    jmh("org.functionaljava:functionaljava:4.8")
    jmh("com.github.gdejohn:procrastination:0.3.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

jmh {
    resultFormat = "JSON"
    profilers = listOf("gc")
    benchmarkMode = listOf("avgt")
    timeUnit = "us"
    fork = 1
    warmupIterations = 1
    iterations = 1
    warmup = "1s"
    timeOnIteration = "1s"
    timeout = "30s"
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    test {
        useJUnitPlatform()
    }
}
