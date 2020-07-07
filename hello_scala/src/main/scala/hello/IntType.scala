
import scala.collection.immutable.List



object IntType {

  def main(args: Array[String]): Unit = {
    val a:List[Int] = List[Int](1,2)
    val b= a.map { _ => "a" }


    println(a)
    println(b)
  }
}

