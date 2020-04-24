package test.vertx

import io.vertx.core.eventbus.Message
import io.vertx.kotlin.core.eventbus.requestAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class VerticleTest1 : CoroutineVerticle() {

    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override suspend fun start() {
        super.start()

        vertx.eventBus().localConsumer<String>("add1").handler {
            log.debug("get requerst: add1: ${it.body()}")
            it.reply("add1 resp: ${it.body()}")
        }

        log.debug("before")
        /* 旧式写法 */
        vertx.eventBus().request<String>("add1", "test3") {
            if (it.succeeded()) {
                vertx.eventBus().request<String>("add1", "msg4(${it.result().body()})"){ ar ->
                    log.debug("get r4: ${ar.result().body()}")
                }
            }
        }

        /* new code */
        val r1 = awaitResult<Message<String>> {
            vertx.eventBus().request("add1", "test1", it)
        }
        log.debug("get r1: ${r1.body()}")
        val r2 = vertx.eventBus().requestAwait<String>("add1", "msg2(${r1.body()})")
        log.debug("get r2: ${r2.body()}")
    }

    override suspend fun stop() {
        super.stop()
    }
}