package test.vertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Verticle
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class VerticleTest1 : AbstractVerticle() {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun start() {
        super.start()
        log.info("in verticle 1 log")
    }

    override fun stop() {
        super.stop()
    }
}