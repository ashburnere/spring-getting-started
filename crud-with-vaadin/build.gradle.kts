plugins {
	java
	id("org.springframework.boot") version "3.1.3"
	id("io.spring.dependency-management") version "1.1.3"
	id("com.vaadin") version "24.3.0"
}

group = "com.example.crudwithvaadin"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.vaadin:vaadin-spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	runtimeOnly("com.h2database:h2")
}

dependencyManagement {
	val vaadinVersion = "24.3.0"
	imports {
		mavenBom("com.vaadin:vaadin-bom:${vaadinVersion}")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
