package hello

object GetPi {
  val sts = 100
  val times = 100000000
  def main(args: Array[String]): Unit = {
    val t0 = System.currentTimeMillis()
    var sum = 0.0
    (0 to sts).foreach { j =>
      sum += (0 to times).map { i =>
        val v:Long = sts*times
        math.pow(-1.0, v) /(2*v+1)
      }.sum
    }
    val t9 = System.currentTimeMillis()
    println(sum*4, s": ${t9-t0}")
  }
}
