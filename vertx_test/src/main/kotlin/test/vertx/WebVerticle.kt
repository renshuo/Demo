package test.vertx

import io.netty.handler.codec.http.HttpRequest
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Route
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.kotlin.core.eventbus.requestAwait
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import kotlin.system.exitProcess

class WebVerticle : CoroutineVerticle() {


    override suspend fun start() {
        val server = vertx.createHttpServer(HttpServerOptions(this.config))
        var router = Router.router(this.vertx)

        router.route("/*").handler(StaticHandler.create());

        router.get("/test").handler(this::test).failureHandler {
            log.error("", it.failure())
        }

        server.requestHandler(router).listen(){
            if (it.failed()) {
                log.error("error to start server: ", it.cause())
                exitProcess(1)
            }else {
                log.info("start web server at ${this.config} success")
            }
        }
    }

    private suspend fun test2(): String {
        val r1 = awaitResult<Message<String>> {
            vertx.eventBus().request("add1", "test1", it)
        }
        log.debug("get r1: ${r1.body()}")
        val r2 = vertx.eventBus().requestAwait<String>("add1", "msg2(${r1.body()})")
        log.debug("get r2: ${r2.body()}")
        return r2.body()
    }

    private fun test(requestHandler: RoutingContext) {
        launch(requestHandler.vertx().dispatcher()) {
            val res = test2()
            requestHandler.response().end(" $res")
        }
    }

//    /**
//     * An extension method for simplifying coroutines usage with Vert.x Web routers
//     */
//    fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
//        handler { ctx ->
//            launch(ctx.vertx().dispatcher()) {
//                try {
//                    fn(ctx)
//                } catch (e: Exception) {
//                    ctx.fail(e)
//                }
//            }
//        }
//    }

    override suspend fun stop() {
        super.stop()
    }
}
