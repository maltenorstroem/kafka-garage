import com.google.protobuf.gradle.*

plugins {
	java
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.google.protobuf") version "0.9.2"
}

group = "com.avismatutina"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

sourceSets{
	main {
		proto {
			proto.srcDir("src/main/resources/proto")
		}
		java {
			srcDirs("build/generated/source/proto/main/java")
		}
	}
}

dependencies {
	
	implementation(project(":required-kafka-bl-client"))
	
	implementation("org.springframework.boot:spring-boot-starter-jersey")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("com.google.protobuf:protobuf-java:3.21.12")
	implementation("com.google.protobuf:protobuf-java-util:3.21.12")


	// https://mvnrepository.com/artifact/org.apache.kafka/kafka-clients
	implementation("org.apache.kafka:kafka-clients:3.0.0")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

val generateProtoDocs by tasks.register<Copy>("generateProtoDocs") {
	dependsOn("generateProto")
	from(layout.buildDirectory.file("generated/source/proto/main/doc"))
	into(layout.projectDirectory.file("doc"))
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.21.12"
	}
	plugins {
		id ("doc") {
			artifact = "io.github.pseudomuto:protoc-gen-doc:1.5.1"
		}
	}
	generateProtoTasks {
		ofSourceSet( "main").forEach {
			it.plugins {
				id("doc") {
					options += "markdown,README.md,source_relative"
				}
			}
		}
	}
}