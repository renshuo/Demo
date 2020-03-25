package test

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.alibaba.fastjson.annotation.JSONField
import io.kotlintest.specs.StringSpec
import java.time.LocalDateTime

class FastJsonTest: StringSpec() {

   init {
       "createJS" {
           val dat1 = Jsa(id=1, name="abc", time = LocalDateTime.now().minusDays(1))

           val obj1 = JSON.toJSON(dat1) as JSONObject
           val str1 = obj1.toJSONString()
           val str2 = JSON.toJSONString(dat1)
           println("$dat1\n$obj1\n$str1")

           val obj2 = JSON.parseObject(str1)
           val dat2 = JSON.parseObject(str1, Jsa::class.java)//jso.toJavaObject(Jsa::class.java)
           val dat3 = obj2.toJavaObject<Jsa>(Jsa::class.java)
           println("$obj2\n$dat2\n$dat3")
       }
   }
}

data class Jsa(
    val id: Int,
    val name: String,
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    val time: LocalDateTime,
    val dataList: List<Jsb> = listOf(Jsb("1"), Jsb("2"))
){
}

data class Jsb(
        val seq: String = "1"
)
