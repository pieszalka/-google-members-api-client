import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.1.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
}

group = "com.fourthwall.googlemembersapi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
}

dependencies {
	//spring
	implementation("org.springframework.boot:spring-boot-starter:2.3.1.RELEASE")
	implementation("org.springframework.boot:spring-boot-starter-web:2.3.1.RELEASE")
	implementation("org.springframework.security:spring-security-oauth2-client:5.3.3.RELEASE")
	implementation("org.springframework.security:spring-security-oauth2-jose:5.3.3.RELEASE")
	implementation("org.springframework.security:spring-security-web:5.3.3.RELEASE")
	implementation("org.springframework.security:spring-security-config:5.3.3.RELEASE")

	//kotlin
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.72")
	implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.72")

	//okhttp
	implementation("com.squareup.moshi:moshi-kotlin:1.9.2")
	implementation("com.squareup.moshi:moshi-adapters:1.9.2")
	implementation("com.squareup.okhttp3:okhttp:4.2.2")
	implementation("com.squareup.okhttp3:logging-interceptor:4.2.2")

	//arrow
	implementation("io.arrow-kt:arrow-core-data:0.10.5")
	implementation("io.arrow-kt:arrow-core:0.10.5")
	implementation("io.arrow-kt:arrow-syntax:0.10.5")
	implementation("io.arrow-kt:arrow-fx:0.10.5")

	//retrofit
	implementation("com.squareup.retrofit2:retrofit:2.6.2")
	implementation("com.squareup.retrofit2:converter-moshi:2.6.2")
	implementation("com.squareup.retrofit2:converter-scalars:2.6.2")

	//jackson
	implementation("com.fasterxml.jackson.core:jackson-core:2.11.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0")
	implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.0")

	//frontend
	implementation("org.thymeleaf:thymeleaf-spring5:3.0.11.RELEASE")

	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
