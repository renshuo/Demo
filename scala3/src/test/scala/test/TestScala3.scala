package test
import org.scalatest._
import flatspec._
import matchers._

class TestScala3 extends AnyFlatSpec with should.Matchers {

  extension(i: Int){
    def testInt():Unit = {
      println("test a int")
    }
    def testInt2(): Double = {
      println(s"in test int2 by value: ${i}")
      math.Pi * i
    }
  }


  "a Int value" should "can call testInt func" in {
    val a:Int = 1
    a.testInt()
    println(a.testInt2())
  }
}
