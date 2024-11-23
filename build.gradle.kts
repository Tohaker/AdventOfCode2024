plugins {
    id("java")
    id("application")
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
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}