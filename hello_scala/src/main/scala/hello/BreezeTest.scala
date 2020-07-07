package hello

import breeze.linalg.DenseVector
import breeze.linalg.operators._
import breeze.linalg.convert._
import breeze.numerics.{exp, sin}
import breeze.numerics.Conversions._

import breeze.optimize._

object BreezeTest {

  def main(args: Array[String]): Unit = {
    val a = DenseVector(1, 2, 3, 4 ,5 )

    val b = a + 1

    val c = a * 2

    val s:DenseVector[Double] = sin(b)


    println(a* b, c )
    println( a.map(_.toDouble) , s , exp(s))

    val x = DenseVector( (1, 2), (11, 12))
    x.map (println)

    println(x)
  }

}
