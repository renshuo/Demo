
plugins {
    kotlin("jvm") version "1.3.70"
    id("com.github.johnrengelman.shadow") version("5.2.0")
    application
}

group = "org.example"
version = "0.1"

repositories {
    mavenCentral()
    //maven("https://mirrors.huaweicloud.com/repository/maven/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("noarg"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    val vertxV: String = "3.9.0"
    implementation("io.vertx:vertx-core:$vertxV")
    implementation("io.vertx:vertx-lang-kotlin:$vertxV")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:$vertxV")
    implementation("io.vertx:vertx-web:$vertxV")
    implementation("io.vertx:vertx-lang-kotlin:$vertxV")
    implementation("io.vertx:vertx-config:$vertxV")
    implementation("io.vertx:vertx-config-yaml:$vertxV")

    implementation("io.vertx:vertx-mongo-client:$vertxV")
    implementation("io.vertx:vertx-jdbc-client:$vertxV")
    implementation("org.mariadb.jdbc:mariadb-java-client:1.5.7")

    implementation("io.vertx:vertx-auth-common:$vertxV")
    implementation("io.vertx:vertx-auth-jdbc:$vertxV")



    val log4jV: String = "2.11.2"
    // val log4jV: String = "2.13.1"
    implementation("org.slf4j:slf4j-api:1.7.28") //slf4j 核心包
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jV") // slf4j log4j2 桥接包
    implementation("org.apache.logging.log4j:log4j-api:$log4jV") // Log4j2核心包
    implementation("org.apache.logging.log4j:log4j-core:$log4jV") // Log4j2核心包

    // for Date data format in kotlin data class, for handling log4j2.yaml
    val jacksonV : String = "2.10.3"
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonV")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonV")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonV")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonV")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:$jacksonV")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonV")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
application {
    applicationName = "vertx_test_app"
    mainClassName = "test.vertx.MainKt"
}