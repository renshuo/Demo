package test

import org.scalatest.flatspec._
import org.scalatest.matchers._
import should._

trait Ani {
  val name:String = ""
}


trait A extends Ani {
  import Unit.given
  def run():Unit
}

trait B extends Ani{
  def swim(): Unit
}

class C1 extends A {
  def run():Unit = {
    println("c1 run")
  }

}


class C1s extends C1 with A with B {
  override def swim(): Unit = {
    println("c1s swim")
  }
}

def callGo(animal: A & B): Unit = {
  animal.swim()
  animal.run()
}

def anyGo(animal: A | B): Unit = {
  animal match {
    case x:A => x.run()
    case x:B => x.swim()
  }
}


class TestNewType extends AnyFlatSpec with should.Matchers {
  "a turtle" should "can run and swim " in {
    val turtle : C1s = new C1s()
    callGo(turtle)
    val tiger: C1 = new C1()
    anyGo(tiger)
  }
}
