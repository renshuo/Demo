package test

object Hello extends App {
  println("Hello world")

  def test1(i: Int) = {
    println(s"helo2 $i")
    i+1
  }

  val res =  test1(1)
  println(res)

  val x1 = (1 to 5) filter{_ > 2} map{_ * 2}
  val x2 = (1 to 5).filter{_ > 2}.map{_ * 2}
  val x3 = (1 to 5).filter(_ > 2).map(_ * 2)
  val x4 = (1 to 5) filter(_ > 2) map(_ * 2)
  println(x1, x2, x3, x4)

  type R = Int
  def compose(g: R => R, h: R => R) =
    (x: R) => g(h(x))

  val y1 = compose(_ + 1, _ * 2)
  val y2 = compose({_ + 1}, {_ * 2})
  println(y1(2), y2(2)) // x*2+1

  val (a, b, c) = (1, 2, 3)
  print(a, b, c)

  if (a==1) {
    println("a is 1")
  } else {
    println("is a not 1?")
  }

  var lst =  List(1, 2, 4)


  for (x <- lst if x > 1 ) {
    print(s", $x")
  }
}
