package test.vertx

import io.vertx.core.Vertx
import io.vertx.core.logging.Log4j2LogDelegateFactory
import io.vertx.core.logging.LoggerFactory.LOGGER_DELEGATE_FACTORY_CLASS_NAME
import org.slf4j.Logger
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("main")

fun main() {
    val vertx = Vertx.vertx()
    System.setProperty(LOGGER_DELEGATE_FACTORY_CLASS_NAME,
            Log4j2LogDelegateFactory::class.java.name)
             // "io.vertx.core.logging.SLF4JLogDelegateFactory"); // for vertx3 use slf4j
    log.warn("test")
    vertx.deployVerticle(VerticleTest1())
}