import com.google.protobuf.gradle.*

plugins {
	id("java")
	id("idea")
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.google.protobuf") version "0.9.2"
}

group = "com.avismatutina"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

sourceSets{
	main {
		proto {
			proto.srcDir("src/main/resources/proto")
		}
	}
}

dependencies {

	implementation("com.google.protobuf:protobuf-java:3.21.12")
	implementation("com.google.protobuf:protobuf-java-util:3.21.12")
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
		id ("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.51.3"
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