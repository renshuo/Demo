package test.vertx

import io.vertx.core.Vertx
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("main")

fun main() {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(CoroutineVerticleTest())
}