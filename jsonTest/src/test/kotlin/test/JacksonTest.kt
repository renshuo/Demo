package test

import arrow.core.toT
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl
import com.fasterxml.jackson.databind.node.JsonNodeFactory
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import io.kotlintest.specs.StringSpec
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class JacksonTest: StringSpec() {
    init {
        "createJson" {
            val om = ObjectMapper()
            val jtm = JavaTimeModule()
                .addSerializer(LocalDateTime::class.java, JacksonDTS())
                .addDeserializer(LocalDateTime::class.java, JacksonSTD())
            om.registerModules(jtm)


            val dat = Jack(1, "name", LocalDateTime.now().minusDays(1))
            val str1  = om.writeValueAsString(dat)
            val obj1  = om.valueToTree<JsonNode>(dat)
            val str2 = obj1.toString()
            println("dat: $dat\nobj: $obj1\nstr: $str1\nstr: $str2")

            val dat2 = om.readValue<Jack>(str2, Jack::class.java)
            val obj2 = om.readTree(str2)
            val dat3  = om.treeToValue<Jack>(obj2, Jack::class.java)
            println("obj: $obj2 \ndat: $dat2")

        }
    }
}

data class Jack(
    val id: Int,
    val name: String,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val time: LocalDateTime
){

    constructor() : this(0,"", LocalDateTime.now()) {
    }
}

class JacksonDTS : JsonSerializer<LocalDateTime>() {
    override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen!!.writeString(value!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
    }
}
class JacksonSTD: JsonDeserializer<LocalDateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        val str = p!!.valueAsString!!
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

}