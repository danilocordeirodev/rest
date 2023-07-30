import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
}

group = "com.oriedroc"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.15.2")
	// https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
	implementation("org.springframework.boot:spring-boot-starter-hateoas")

	implementation("com.github.dozermapper:dozer-core:6.4.1")


//    implementation("org.flywaydb:flyway-core")
//	implementation("org.flywaydb:flyway-mysql")
//	testImplementation("org.springframework.boot:spring-boot-testcontainers")
//	testImplementation("org.testcontainers:junit-jupiter")
//	testImplementation("org.testcontainers:mysql")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
