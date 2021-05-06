package sc3


import org.scalatest._
import org.scalatest.flatspec._
import org.scalatest.matchers.should

class TestNewFeature extends AnyFlatSpec with should.Matchers {

  "match test " should "dispatch by value" in {
    println("hello world")
  }
}
