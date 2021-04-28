package test.vertx

import io.vertx.core.AsyncResult
import io.vertx.core.Future
import io.vertx.core.Handler
import io.vertx.core.eventbus.Message
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.AbstractUser
import io.vertx.ext.auth.AuthProvider
import io.vertx.ext.auth.User
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import io.vertx.kotlin.coroutines.CoroutineVerticle
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AuthTest: CoroutineVerticle()  {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)


    override suspend fun start() {
        vertx.eventBus().consumer<JsonObject>("auth") {msg ->
            log.debug("get auth : ${msg.body()}")

        }
    }
}

class MyAuthProvider: AuthProvider {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun authenticate(authInfo: JsonObject, resultHandler: Handler<AsyncResult<User>>) {

        val u = authInfo.getString("username")
        val p = authInfo.getString("password")
        if (u.equals("sren") && p.equals("qwe")) {
            resultHandler.handle(Future.succeededFuture(MyUser(authInfo)))
        } else {
            resultHandler.handle(Future.failedFuture("password error"))
        }
    }

}

class MyUser(val authInfo: JsonObject): User {

    override fun clearCache(): User {
        TODO("Not yet implemented")
    }

    override fun setAuthProvider(authProvider: AuthProvider?) {
        TODO("Not yet implemented")
    }

    override fun isAuthorized(authority: String, resultHandler: Handler<AsyncResult<Boolean>>): User {
        resultHandler.handle(Future.succeededFuture(true))
        return this
    }

    override fun principal(): JsonObject {
        return authInfo
    }

}
