package test.vertx

import io.vertx.core.Vertx
import io.vertx.core.logging.Log4j2LogDelegateFactory
import io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("main")

fun main() {
    val vertx = Vertx.vertx()
    vertx.deployVerticle(VerticleTest1())
}