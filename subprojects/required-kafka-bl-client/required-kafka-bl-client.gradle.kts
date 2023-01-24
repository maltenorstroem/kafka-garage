plugins {
    java
    id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
}

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
	implementation("org.apache.kafka:kafka-clients:3.0.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}