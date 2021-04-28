package test



import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.scala._

object WordCountTest {

  def main(args: Array[String]): Unit = {
    val port = 9012
    val host = "192.168.1.110"

    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val text = env.socketTextStream(host, port, '\n')

    val windowCounts = text
      .flatMap { w => w.split("\\s") }
      .map { w => WordWithCound(w, 1)}
      .keyBy("word")
      .timeWindow(Time.seconds(5), Time.seconds(1))
      .sum("count")

    windowCounts.print().setParallelism(1)

    env.execute("Socket window wordCount")


  }

  case class WordWithCound(word: String, count: Long)
}
