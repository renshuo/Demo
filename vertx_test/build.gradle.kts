plugins {
    kotlin("jvm") version "1.3.70"
    application
}

group = "org.example"
version = "0.1"

repositories {
    //mavenCentral()
    maven("https://mirrors.huaweicloud.com/repository/maven/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation(kotlin("noarg"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

    implementation("io.vertx:vertx-core:3.9.0")
    implementation("io.vertx:vertx-lang-kotlin:3.9.0")

    implementation("org.slf4j:slf4j-api:1.7.28") //slf4j 核心包
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.13.1") // slf4j log4j2 桥接包
    implementation("org.apache.logging.log4j:log4j-api:2.13.1") // Log4j2核心包
    implementation("org.apache.logging.log4j:log4j-core:2.13.1") // Log4j2核心包

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
    mainClassName = "test.vertx.MainKt"
}