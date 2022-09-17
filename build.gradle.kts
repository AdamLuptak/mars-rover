plugins {
    java
    jacoco
}

group = "eu.rebase"
version = "1.0-SNAPSHOT"
val projectMainClass = "eu.rebase.marsrover.Application"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = projectMainClass
    }
}

tasks.register<JavaExec>("runMarsRoverApplication") {
    val roverInitState: String by project
    val roverCommands: String by project

    description = "Run the program Mars Rover"
    mainClass.set(projectMainClass)
    classpath = sourceSets["main"].runtimeClasspath
    args(setOf(roverInitState, roverCommands))
}
