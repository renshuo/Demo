package test.jackson

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import java.time.LocalDateTime

data class Jack(
    val id: Int = 0
){
    @JacksonXmlElementWrapper(localName="listList")
    @JacksonXmlProperty(localName = "listElm")
    val subJackList: List<SubJack> = listOf(SubJack(), SubJack())

    override fun toString(): String {
        return "Jack(id=$id, subJackList=$subJackList)"
    }
}

data class SubJack(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        val time: LocalDateTime = LocalDateTime.now()
)