import com.google.protobuf.gradle.*

plugins {
    java
    id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
    id("com.google.protobuf") version "0.9.2"
}

java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(":common-typelibrary"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    // https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
	implementation("org.apache.kafka:kafka-clients:3.0.0")

    implementation("com.google.protobuf:protobuf-java:3.21.12")
    implementation("com.google.protobuf:protobuf-java-util:3.21.12")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}