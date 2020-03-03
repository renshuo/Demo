package test

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import io.kotlintest.specs.StringSpec
import java.time.LocalDateTime

class JacksonXmlTest: StringSpec() {

    init {
        "print pretty xml" {
            val req = Jack(1, "name", LocalDateTime.now().minusDays(1))
            // use jackson (used by springboot) convert it to XML
            val om = XmlMapper()
            om.enable(SerializationFeature.INDENT_OUTPUT)
            val xml = om.writeValueAsString(req);
            print(xml)
        }
    }

}