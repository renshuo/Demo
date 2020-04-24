package test.vertx

import io.netty.handler.codec.http.HttpRequest
import io.vertx.core.Handler
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.kotlin.coroutines.CoroutineVerticle
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
                log.error("erro to start server: ", it.cause())
                exitProcess(1)
            }else {
                log.info("start web server at ${this.config} success")
            }
        }
    }

    fun test(requestHandler: RoutingContext) {
        log.debug("in test")
        requestHandler.response().end("hello world ")
    }

    override suspend fun stop() {
        super.stop()
    }
}
