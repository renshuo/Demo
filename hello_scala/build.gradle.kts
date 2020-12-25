
plugins {
    application
    java
    scala
}

group = "scala_hello"
version = "1"

application {
    mainClassName = "hello.scalafx.ScalaFxFui"
}

java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    // mavenCentral()
    maven("https://mirrors.huaweicloud.com/repository/maven/")
}



dependencies {
    val scalaVersion = "2.13.3"
    val scalaSV = "2.13"
    implementation("org.scala-lang:scala-library:$scalaVersion")
    // scala parallel connection https://github.com/scala/scala-parallel-collections
    implementation("org.scala-lang.modules:scala-parallel-collections_$scalaSV:0.2.0")

    //breeze https://github.com/scalanlp/breeze
    implementation("org.scalanlp:breeze_$scalaSV:1.0")
    implementation("org.scalanlp:breeze-viz_$scalaSV:1.0")

    //scimage https://github.com/sksamuel/scrimage
    val scimagev = "4.0.5"
    implementation("com.sksamuel.scrimage:scrimage-core:$scimagev")
    implementation("com.sksamuel.scrimage:scrimage-filters:$scimagev")
    implementation("com.sksamuel.scrimage:scrimage-formats-extra:$scimagev")
    implementation("com.sksamuel.scrimage:scrimage-scala_$scalaSV:$scimagev")

    //imageIO https://github.com/haraldk/TwelveMonkeys
    val tmv = "3.6"
    implementation("com.twelvemonkeys.imageio:imageio-core:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-bmp:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-jpeg:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-tiff:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-pnm:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-psd:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-hdr:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-iff:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-pcx:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-pict:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-sgi:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-tga:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-icns:$tmv")
    implementation("com.twelvemonkeys.imageio:imageio-thumbsdb:$tmv")

    //jcuda
    //jcuda
    val classifier = "linux-x86_64"
    val jCudaVersion = "11.0.0"
    implementation("org.jcuda:jcuda:$jCudaVersion") {
        isTransitive = false
    }
    implementation("org.jcuda:jcufft:$jCudaVersion") {
        isTransitive = false
    }
    implementation("org.jcuda", "jcuda-natives",     classifier = classifier, version = jCudaVersion         )
    implementation("org.jcuda", "jcublas-natives",   classifier = classifier, version = jCudaVersion       )
    implementation("org.jcuda", "jcufft-natives",    classifier = classifier, version = jCudaVersion        )
    implementation("org.jcuda", "jcusparse-natives", classifier = classifier, version = jCudaVersion     )
    implementation("org.jcuda", "jcurand-natives",   classifier = classifier, version = jCudaVersion       )
    implementation("org.jcuda", "jcusolver-natives", classifier = classifier, version = jCudaVersion     )
    //implementation("org.jcuda", "jnvgraph-natives",  classifier = classifier, version = jCudaVersion      )
    //implementation("org.jcuda", "jcudnn-natives",    classifier = classifier, version = jCudaVersion        )


    // AKKA
    implementation("com.typesafe.akka:akka-actor-typed_$scalaSV:2.6.8")


    // scalaFx
    var jfxClassifier = "linux"
    val jfxVersion = "15.0.1"
    implementation(group ="org.scalafx", name="scalafx_2.13", version="14-R19")
    implementation(group= "org.openjfx", name= "javafx-base",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-controls",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-graphics",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-media",  classifier= jfxClassifier ,version=jfxVersion)
    jfxClassifier = "win"
    implementation(group= "org.openjfx", name= "javafx-base",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-controls",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-graphics",  classifier= jfxClassifier ,version=jfxVersion)
    implementation(group= "org.openjfx", name= "javafx-media",  classifier= jfxClassifier ,version=jfxVersion)


    //slf4j
    val slf4jversion = "1.7.30"
    val log4jversion = "2.13.3"
    val jacksonversion = "2.11.0"
    implementation("org.slf4j:slf4j-api:$slf4jversion") //slf4j 核心包
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jversion") // slf4j log4j2 桥接包
    implementation(group= "org.apache.logging.log4j", name= "log4j-api", version = log4jversion) // Log4j2核心包
    implementation(group= "org.apache.logging.log4j", name= "log4j-core", version= log4jversion) // Log4j2核心包
    // add jackson dep for identify log4j2.yaml type config
    implementation(group="com.fasterxml.jackson.core", name="jackson-annotations", version=jacksonversion)
    implementation(group="com.fasterxml.jackson.core", name="jackson-core", version=jacksonversion)
    implementation(group="com.fasterxml.jackson.dataformat", name="jackson-dataformat-yaml", version=jacksonversion)

    // vert.x
//    val vertxVersion="3.9.2"
//    implementation("io.vertx:vertx-lang-scala_$scalaSV:$vertxVersion")
//    implementation("io.vertx:vertx-core:$vertxVersion")
//    implementation("io.vertx:vertx-web:$vertxVersion")
//    implementation("io.vertx:vertx-config:$vertxVersion")
//    implementation("io.vertx:vertx-config-yaml:$vertxVersion")

    testImplementation(group= "junit", name= "junit", version= "4.12")
}
