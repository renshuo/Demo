package hello

object TestFor {
  def main(args: Array[String]): Unit = {

    val a1 = Array(1,2,3)
    val a2 = Array("a", "b", "c")
    val d = for{ a <- a1; b <- a2} yield (a,b)

  }
}
