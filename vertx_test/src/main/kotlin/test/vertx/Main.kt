package test.vertx

import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.kotlin.core.json.*
import org.slf4j.LoggerFactory

val log = LoggerFactory.getLogger("main")

fun main() {
    vertxConfig().getConfig(){
        val result = it.result()
        val newVx = Vertx.vertx(VertxOptions(result))
        newVx.deployVerticle(CoroutineVerticleTest(), DeploymentOptions(result.getJsonObject("CoroutineVerticleTest")))
    }

}

fun vertxConfig(): ConfigRetriever {
    val vertx = Vertx.vertx()
    val cro = json{
        obj(
        "stores" to array(
            obj(
                "type" to "file",
                "config" to obj(
                    "path" to "vertx.yaml"
                ),
                "format" to "yaml"
            ),
            obj(
                "type" to "sys"
            ),
            obj(
                "type" to "env"
            )
        )
    )}
    val conf = ConfigRetrieverOptions(cro)
    val cr = ConfigRetriever.create(vertx, conf)
    return cr
}