package hello

object HighFunTest {

  def HFTfun(name: String): String = {
    return s"hello $name"
  }
}


object test {

  def main(args: Array[String]): Unit = {
    test(HighFunTest.HFTfun)
  }
  def test(f: String => String) {
    val res = f("test")
    println(res)
  }
}

