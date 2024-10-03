plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	implementation("javax.activation:activation:1.1.1")
	implementation("org.glassfish.jaxb:jaxb-runtime:2.3.3")
	implementation("com.aliyun.oss:aliyun-sdk-oss:3.17.4")
	implementation("com.github.pagehelper:pagehelper-spring-boot-starter:1.4.0")
	implementation("com.auth0:java-jwt:4.2.1")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.projectlombok:lombok")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.mybatis.spring.boot:mybatis-spring-boot-starter-test:3.0.3")
	runtimeOnly("com.mysql:mysql-connector-j")
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
