package test.vertx

import io.vertx.core.WorkerExecutor
import io.vertx.core.json.JsonObject
import io.vertx.ext.jdbc.JDBCClient
import io.vertx.kotlin.coroutines.CoroutineVerticle
import io.vertx.kotlin.coroutines.awaitBlocking
import io.vertx.kotlin.ext.sql.getConnectionAwait
import io.vertx.kotlin.ext.sql.queryAwait
import kotlinx.coroutines.runBlocking

class JdbcWorker : CoroutineVerticle() {


    override suspend fun start() {
        val client = JDBCClient.createShared(vertx, config)

        val c = vertx.eventBus().consumer<JsonObject>("jdbc")

        c.handler {msg ->
            log.debug("get jdbc param : ${msg.body()}")
            client.getConnection {conn ->
                if (conn.succeeded()) {
                    conn.result().query("select * from Station") {
                        msg.reply(it.result().toJson())
                        conn.result().close() // return the conn
                    }
                }
            }
        }
    }
}