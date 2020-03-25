plugins {
    kotlin("jvm") version "1.3.61"

}

group = "test"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    //for test voertx json lib
    implementation("io.vertx:vertx-core:3.8.4")
    implementation("io.vertx:vertx-lang-kotlin:3.8.4")


    //ali88
    implementation("com.alibaba:fastjson:1.2.61")

    //gson
    implementation("com.google.code.gson:gson:2.8.6")

    //jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.1")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.10.1")

    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.4.2")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}