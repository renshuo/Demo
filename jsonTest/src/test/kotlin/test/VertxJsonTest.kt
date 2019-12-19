package test

import io.kotlintest.inspectors.buildAssertionError
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import io.vertx.core.buffer.Buffer
import io.vertx.core.json.pointer.JsonPointer
import io.vertx.core.parsetools.JsonParser
import io.vertx.kotlin.core.json.Json
import io.vertx.kotlin.core.json.array
import io.vertx.kotlin.core.json.json
import io.vertx.kotlin.core.json.obj
import java.time.LocalDateTime

class VertxJsonTest : StringSpec() {

    fun <T> json(block: Json.() -> T): T = Json.block()
    fun test1(a : String) {}


    init {
        "createJson" {
            val js1 = json {
                obj(
                    "a" to 1,
                    "b" to obj (
                        "b1" to LocalDateTime.now()
                    ),
                    "c" to obj((1..3).map { "key_$it" to it }),
                    "d" to obj {
                        for (i in 1..3) {
                            put("key_$i", i)
                        }
                    }

                )
            }
            println("get js obj: ${js1.encodePrettily()}")
        }
        "queryJson" {
            val js = json {
                obj(
                    "a" to 1,
                    "b" to obj(
                        "c" to 2
                    )
                )
            }
            val p = JsonPointer.from("/b/c")
            val result = p.queryJson(js)
            result shouldBe 2
        }

        "f:jsonParser" {
            val p = JsonParser.newParser()
            val j = p.handle(Buffer.buffer("""
                { a: { b:2, c: "2019-12-12 21:12:12" } }
            """.trimIndent()))
            p.end()

        }

    }




}