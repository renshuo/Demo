package hx

import java.util
import java.util.concurrent.ForkJoinPool
import scala.concurrent.ExecutionContext
import scala.reflect.api.Universe


trait Ord[T] {
  def compare(x:T, y:T): Int

  extension (x:T)
    def gt(y:T) = {
      compare(x, y) < 0
    }
}

given intOrd: Ord[Int] with {
  override def compare(x: Int, y: Int): Int = if x>y then 1 else 0
}

given flrOrd: Ord[Float] with {
  override def compare(x: Float, y: Float): Int = if x>y then 1 else 0
}

class dblOrd extends Ord[Double] {
  def compare(x: Double, y:Double):Int = {
    1
  }
}

def testGiven(args: String*) = {
  println("test given")
  val i:Int = 0
  val j:Int = 1
}
