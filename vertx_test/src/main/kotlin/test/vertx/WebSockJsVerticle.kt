package test.vertx

import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.sockjs.SockJSHandler
import io.vertx.ext.web.handler.sockjs.SockJSHandlerOptions
import io.vertx.ext.web.handler.sockjs.SockJSSocket
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.ext.web.handler.sockjs.sockJSHandlerOptionsOf
import kotlinx.coroutines.runBlocking

class WebSockJsVerticle : CoroutineVerticle() {

    override suspend fun start() {
        val router = Router.router(vertx)

        val options = SockJSHandlerOptions(this.config)

        router.route("/wss/test/*").handler(
            SockJSHandler.create(vertx, options).apply {
                socketHandler(this@WebSockJsVerticle::test1)
            })

        vertx.createHttpServer(HttpServerOptions(this.config)).requestHandler(router).listen()
    }

    private fun test1(sock: SockJSSocket) {
        sock.handler {
            log.debug(it.toString("UTF-8"))
        }
        sock.write("hello world")
    }

    override suspend fun stop() {
        super.stop()
    }
}