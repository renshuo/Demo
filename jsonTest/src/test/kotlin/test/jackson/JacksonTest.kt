package test.jackson

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotlintest.specs.StringSpec
import java.time.LocalDateTime

class JacksonTest: StringSpec() {
    init {
        "J" {
            val om = getOM()

            val dat = Jack(1)
            val str1  = om.writeValueAsString(dat)
            val obj1  = om.valueToTree<JsonNode>(dat)
            val str2 = obj1.toString()
            println("dat: $dat\nobj: $obj1\nstr: $str1\nstr: $str2")

            val dat2 = om.readValue<Jack>(str2, Jack::class.java)
            val obj2 = om.readTree(str2)
            val dat3  = om.treeToValue<Jack>(obj2, Jack::class.java)
            println("obj: $obj2 \ndat: $dat2")

        }

        "convert" {
            val obj = Jack(2)
            val json = getOM().writeValueAsString(obj)
            println(json)
            val obj2 = getOM().readValue(json, Jack::class.java)
            println(obj2)
        }
    }

    private fun getOM(): ObjectMapper {
        val om = ObjectMapper()
        val jtm = JavaTimeModule()
                .addSerializer(LocalDateTime::class.java, JacksonDTS())
                .addDeserializer(LocalDateTime::class.java, JacksonSTD())
        om.registerModules(jtm)
        om.registerKotlinModule()
        om.enable(SerializationFeature.INDENT_OUTPUT)
        return om
    }
}

