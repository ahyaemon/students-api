import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jooq.meta.jaxb.Property

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"

	// jooq
	id("nu.studer.jooq") version "7.1.1"
}

group = "com.example"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "mockito-core")
	}
	testImplementation("com.ninja-squad:springmockk:3.1.1")

	// jooq
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	jooqGenerator("com.h2database:h2")
	jooqGenerator("org.jooq:jooq-meta-extensions")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")

	// kotest
	testImplementation("io.kotest:kotest-assertions-core:5.2.2")

	// mockk
	testImplementation("io.mockk:mockk:1.12.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// https://github.com/etiennestuder/gradle-jooq-plugin/issues/222
// https://github.com/etiennestuder/gradle-jooq-plugin/blob/master/src/test/groovy/nu/studer/gradle/jooq/JooqFuncTest.groovy
jooq {
	version.set("3.16.5")

	configurations {
		create("main") {
			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN
				jdbc = null

				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator"
					database.apply {
						name = "org.jooq.meta.extensions.ddl.DDLDatabase"
						properties.addAll(
							listOf(
								Property().apply {
									key = "scripts"
									value = "src/main/resources/schema.sql"
								},
							)
						)
					}
					generate.apply {
						isPojosAsKotlinDataClasses = true
					}
					target.apply {
						packageName = "com.example.demo.jooq"
						directory = "build/generated-src/jooq/main"  // default (can be omitted)
					}
					strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
				}
			}
		}
	}
}
tasks.named<nu.studer.gradle.jooq.JooqGenerate>("generateJooq") { allInputsDeclared.set(true) }
