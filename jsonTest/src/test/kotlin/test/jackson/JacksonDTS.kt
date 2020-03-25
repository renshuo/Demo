package test.jackson

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class JacksonDTS(val dts: String ="yyyy-MM-dd HH:mm:ss" ) : JsonSerializer<LocalDateTime>() {
    override fun serialize(value: LocalDateTime?, gen: JsonGenerator?, serializers: SerializerProvider?) {
        gen!!.writeString(value!!.format(DateTimeFormatter.ofPattern(dts)))
    }
}

class JacksonSTD(val dts: String ="yyyy-MM-dd HH:mm:ss" ): JsonDeserializer<LocalDateTime>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): LocalDateTime {
        val str = p!!.valueAsString!!
        return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(dts))
    }
}