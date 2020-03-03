import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	application
	kotlin("jvm") version "1.3.61"
	id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "ppjs"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	maven("https://mirrors.huaweicloud.com/repository/maven/")
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation(kotlin("reflect"))

	// compile "io.vertx:vertx-core:3.8.5"
	implementation("io.vertx:vertx-core:3.8.5")
	implementation("io.vertx:vertx-lang-kotlin:3.8.5")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {

	manifest {
		//attributes( )
		attributes(mapOf("Main-Class" to "webgl.ppjs.MainKt"))
	}

}

//shadowJar {
//	classifier = 'fat'
//	manifest {
//		attributes 'Main-Verticle': 'io.vertx.example.HelloWorldVerticle'
//	}
//	mergeServiceFiles {
//		include 'META-INF/services/io.vertx.core.spi.VerticleFactory'
//	}
//}

application.mainClassName = "io.vertx.core.Launcher"


