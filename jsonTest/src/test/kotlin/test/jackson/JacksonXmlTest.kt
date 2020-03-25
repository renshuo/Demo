package test.jackson

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import io.kotlintest.specs.StringSpec
import java.time.LocalDateTime



class JacksonXmlTest: StringSpec() {
    init {
        "test create xml" {
            val om = getXmlSerializer(true)

            val obj = Jack()

            val xml = om.writeValueAsString(obj)
            println(xml)

            val obj2 = om.readValue(xml, Jack::class.java)
            println(obj2)
        }
    }
}


fun getXmlSerializer(indent: Boolean = false, dateFormat: String = "yyyy-MM-dd HH:mm:ss"): XmlMapper {
    val module = JacksonXmlModule()
    module.setDefaultUseWrapper(true)
    val m = XmlMapper(module)
    val jtm = JavaTimeModule()
            .addSerializer(LocalDateTime::class.java, JacksonDTS(dateFormat))
            .addDeserializer(LocalDateTime::class.java, JacksonSTD(dateFormat))
    m.registerModule(jtm)
    m.registerKotlinModule()
    if (indent) m.enable(SerializationFeature.INDENT_OUTPUT)
    return m
}