package test.vertx

import io.vertx.core.eventbus.Message
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.StaticHandler
import io.vertx.kotlin.core.eventbus.requestAwait
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitResult
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.*
import java.util.*
import kotlin.system.exitProcess

class WebVerticle : CoroutineVerticle() {


    override suspend fun start() {
        val server = vertx.createHttpServer(HttpServerOptions(this.config))
        var router = Router.router(this.vertx)

        router.route("/*").handler(StaticHandler.create());

        router.get("/test").handler(this::test).failureHandler {
            log.error("", it.failure())
        }

        router.get("/test2").handler(this::testAwait).failureHandler {
            log.error("", it.failure())
        }

        router.get("/auth").handler(this::auth).failureHandler {
            log.error("", it.failure())
        }

        router.get("/jdbc").handler(this::jdbc).failureHandler {
            log.error("", it.failure())
        }

        server.requestHandler(router).listen() {
            if (it.failed()) {
                log.error("error to start server: ", it.cause())
                exitProcess(1)
            } else {
                log.info("start web server at ${this.config} success")
            }
        }
    }

    private fun jdbc(ctx: RoutingContext){
        val paramJson = json {
            obj(
                ctx.request().params().map {
                    "${it.key}"  to "${it.value}"
                }
            )
        }
        log.debug("get http params: ${ctx.request().params()}")
        vertx.eventBus().request<JsonObject>("jdbc", paramJson) {
            if (it.succeeded()) {
                val res = it.result().body()
                ctx.response().end(res.toString())
            }
        }
    }

    private fun auth(ctx: RoutingContext) {
        log.debug("get http header: ${ctx.request().headers()}")
        log.debug("get http params: ${ctx.request().params()}")
        log.debug("get http: bodyAsJson: ${ctx.bodyAsJson}")
        val ba = ctx.request().headers().get("authorization")
        val up = String(Base64.getDecoder().decode(ba.split(" ")[1]))
        val (u:String, p: String) = up.split(":")
        log.debug("get username and password: $u $p")
        val j = jsonObjectOf(
            "username" to ctx.request().getParam("username"),
            "password" to ctx.request().getParam("password")

        )
        MyAuthProvider().authenticate(j) {
            val user = it.result()
            val j = JsonObject.mapFrom(user)
            user.isAuthorised("login"){authresult ->
                if (authresult.succeeded()) {
                    log.debug("get auth user info: $j login result: $authresult")
                    ctx.response().end("${authresult.result()}")
                }
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
        val j = launch(requestHandler.vertx().dispatcher(), start = CoroutineStart.LAZY) {
            val res = test2()
            requestHandler.response().end(" $res")
        }
        j.start()
    }

    private fun testAwait(ctx: RoutingContext) = runBlocking {
        val res = async (ctx.vertx().dispatcher()) {
            test2()
        }
        val result = res.await()
        ctx.response().end(result)
    }

    private fun testBlocking(ctx: RoutingContext) {
        val res = runBlocking {
            test2()
        }
        ctx.response().end(res)
    }

    private fun testWith(ctx: RoutingContext) = runBlocking{
            val res = withContext(ctx.vertx().dispatcher()) {
                test2()
            }
            ctx.response().end(res)
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
