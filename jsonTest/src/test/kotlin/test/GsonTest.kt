package test

import arrow.core.toT
import com.google.gson.*
import io.kotlintest.specs.StringSpec
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GsonTest: StringSpec() {
    init {
        "creatJson" {
            val gb = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss") // for Date, Timestamp, java.sql.Date
                .registerTypeAdapter(LocalDateTime::class.java, LDTSerializer()) // custom LocalDateTime conv
                .create()

            val dat1 = Jsg(1, "a", LocalDateTime.now().minusDays(1))
            val obj1 = gb.toJsonTree(dat1)
            val str1 = obj1.toString()
            val str2 = gb.toJson(dat1)
            println("$dat1,\n$obj1, \n$str1\n$str2")

            val obj2 = gb.fromJson(str1, JsonElement::class.java)
            val dat2 = gb.fromJson<Jsg>(obj2, Jsg::class.java)
            val dat3 = gb.fromJson<Jsg>(str1, Jsg::class.java)
            println("$obj2, \n$dat2\n$dat3")
        }
    }
}

data class Jsg(
    val id: Int,
    val name: String,
    val time: LocalDateTime
)

class LDTSerializer: JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    override fun serialize(src: LocalDateTime?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src!!.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): LocalDateTime {
        when(json){
            is JsonPrimitive -> {
                return LocalDateTime.parse(json!!.asJsonPrimitive.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            }
        }
        return LocalDateTime.parse(json!!.asString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }
}