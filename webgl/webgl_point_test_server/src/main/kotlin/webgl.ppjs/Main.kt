package webgl.ppjs

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx

class Main: AbstractVerticle() {

    override fun start() {
        println("hello ")
    }
}

fun main() {
    val v = Vertx.vertx()
    v.deployVerticle(WebSockVert::class.java.name)
}

