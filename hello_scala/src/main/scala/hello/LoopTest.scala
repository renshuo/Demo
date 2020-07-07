package hello

import scala.collection.parallel.CollectionConverters._

object LoopTest {

  def main(args: Array[String]): Unit = {

    val s = System.currentTimeMillis()

    def isDivis(x:Int) = (1 to 16) forall {x % _ == 0}
    def find(n:Int):Int = if (isDivis(n)) n else find (n+2)
    println (find (2))
    val e = System.currentTimeMillis()
    println(e-s)

  }
}
