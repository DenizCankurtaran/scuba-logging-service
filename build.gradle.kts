plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
  	id("org.jetbrains.kotlin.plugin.jpa") version "2.0.20"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-all:1.1.10.RELEASE")
	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.1.4")
	implementation("org.springframework.boot:spring-boot-starter-jdbc:3.3.3")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.3.3")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	// implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.2")
	//implementation("org.glassfish.jaxb:jaxb-runtime:4.0.5")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}

}

tasks.withType<Test> {
	useJUnitPlatform()
}
