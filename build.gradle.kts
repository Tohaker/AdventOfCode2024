plugins {
    id("java")
    id("application")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(23))
    }
}

application {
    mainClass = if (project.hasProperty("mainClass")) project.property("mainClass").toString() else "NULL"
}

group = "org.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.github.dpaukov:combinatoricslib3:3.4.0")
}

tasks.test {
    useJUnitPlatform()
}