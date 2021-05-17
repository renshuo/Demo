package test

import org.scalatest._
import flatspec._
import matchers._


class TestUsing extends AnyFlatSpec with should.Matchers {

  def func1(using i: Int) = {
    println(s"in func1 ${i}")
  }

  it should "test given, using" in {
    println("test given and using ")
    implicit val j:Int = 9
    implicit val i: Int = 2
    func1(using i)

  }

}

